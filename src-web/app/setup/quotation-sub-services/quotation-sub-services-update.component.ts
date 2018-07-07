
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { QuotationSubServicesService } from '../../shared/service/quotation-sub-services.service';

import { QuotationSubServices } from '../../shared/model/quotation-sub-services.model';
            import { Company } from '../../shared/model/company.model';
            import { CompanyService} from '../../shared/service/company.service';
            import { OrderSubServicesType } from '../../shared/model/order-sub-services-type.model';
            import { OrderSubServicesTypeService} from '../../shared/service/order-sub-services-type.service';
            import { OrderSubServices } from '../../shared/model/order-sub-services.model';
            import { OrderSubServicesService} from '../../shared/service/order-sub-services.service';
            import { Quotation } from '../../shared/model/quotation.model';
            import { QuotationService} from '../../shared/service/quotation.service';

@Component({
    selector: 'jhi-quotation-sub-services-update',
    templateUrl: './quotation-sub-services-update.component.html'
})
export class QuotationSubServicesUpdateComponent implements OnInit {

    private _quotationSubServices: QuotationSubServices;
    isSaving: boolean;
    private currentAccount : any;

    ordersubservicestypes: OrderSubServicesType[];

    ordersubservices: OrderSubServices[];

    quotations: Quotation[];

    constructor(
        private alertService: AlertService,
        private quotationSubServicesService: QuotationSubServicesService,
        private principal : Principal,
        private companyService: CompanyService,
        private orderSubServicesTypeService: OrderSubServicesTypeService,
        private orderSubServicesService: OrderSubServicesService,
        private quotationService: QuotationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({quotationSubServices}) => {
            this.quotationSubServices = quotationSubServices;
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.orderSubServicesTypeService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<OrderSubServicesType[]>) => { this.ordersubservicestypes = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            this.orderSubServicesService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<OrderSubServices[]>) => { this.ordersubservices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            this.quotationService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<Quotation[]>) => { this.quotations = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        });

    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.quotationSubServices.id !== undefined) {
            this.subscribeToSaveResponse(
                this.quotationSubServicesService.update(this.quotationSubServices));
        } else {
            this.quotationSubServices.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.quotationSubServicesService.create(this.quotationSubServices));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<QuotationSubServices>>) {
        result.subscribe((res: HttpResponse<QuotationSubServices>) =>
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

    trackOrderSubServicesTypeById(index: number, item: OrderSubServicesType) {
        return item.id;
    }

    trackOrderSubServicesById(index: number, item: OrderSubServices) {
        return item.id;
    }

    trackQuotationById(index: number, item: Quotation) {
        return item.id;
    }
    get quotationSubServices() {
        return this._quotationSubServices;
    }

    set quotationSubServices(quotationSubServices: QuotationSubServices) {
        this._quotationSubServices = quotationSubServices;
    }
}
