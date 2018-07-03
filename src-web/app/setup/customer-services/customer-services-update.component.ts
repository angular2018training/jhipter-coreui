
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { CustomerServicesService } from './customer-services.service';

import { CustomerServices } from './customer-services.model';
            import { Company, CompanyService } from '../company';
            import { OrderServices, OrderServicesService } from '../order-services';
            import { Quotation, QuotationService } from '../quotation';
            import { Customer, CustomerService } from '../customer';

@Component({
    selector: 'jhi-customer-services-update',
    templateUrl: './customer-services-update.component.html'
})
export class CustomerServicesUpdateComponent implements OnInit {

    private _customerServices: CustomerServices;
    isSaving: boolean;
    private currentAccount : any;

    orderservices: OrderServices[];

    quotations: Quotation[];

    customers: Customer[];

    constructor(
        private alertService: AlertService,
        private customerServicesService: CustomerServicesService,
        private principal : Principal,
        private companyService: CompanyService,
        private orderServicesService: OrderServicesService,
        private quotationService: QuotationService,
        private customerService: CustomerService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({customerServices}) => {
            this.customerServices = customerServices;
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.orderServicesService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<OrderServices[]>) => { this.orderservices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            this.quotationService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<Quotation[]>) => { this.quotations = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            this.customerService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<Customer[]>) => { this.customers = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        });

    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.customerServices.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerServicesService.update(this.customerServices));
        } else {
            this.customerServices.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.customerServicesService.create(this.customerServices));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CustomerServices>>) {
        result.subscribe((res: HttpResponse<CustomerServices>) =>
            this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.alertService.error(errorMessage, null, null);
    }

    trackCompanyById(index: number, item: Company) {
        return item.id;
    }

    trackOrderServicesById(index: number, item: OrderServices) {
        return item.id;
    }

    trackQuotationById(index: number, item: Quotation) {
        return item.id;
    }

    trackCustomerById(index: number, item: Customer) {
        return item.id;
    }
    get customerServices() {
        return this._customerServices;
    }

    set customerServices(customerServices: CustomerServices) {
        this._customerServices = customerServices;
    }
}
