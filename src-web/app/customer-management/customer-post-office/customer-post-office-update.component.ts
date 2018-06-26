import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from '../../shared/constants/input.constants';
import { JhiAlertService,  } from 'ng-jhipster';

import { CustomerPostOfficeService } from './customer-post-office.service';

import { CustomerPostOffice } from './customer-post-office.model';
            import { Customer, CustomerService } from '../customer';
            import { Company, CompanyService } from '../../setup/company';
            import { PostOffice, PostOfficeService } from '../../setup/post-office';

@Component({
    selector: 'jhi-customer-post-office-update',
    templateUrl: './customer-post-office-update.component.html'
})
export class CustomerPostOfficeUpdateComponent implements OnInit {

    private _customerPostOffice: CustomerPostOffice;
    isSaving: boolean;

    customers: Customer[];

    companies: Company[];

    postoffices: PostOffice[];
    createDate: string;

    constructor(
        private jhiAlertService: JhiAlertService,
        private customerPostOfficeService: CustomerPostOfficeService,
        private customerService: CustomerService,
        private companyService: CompanyService,
        private postOfficeService: PostOfficeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({customerPostOffice}) => {
            this.customerPostOffice = customerPostOffice;
        });
        this.customerService.query()
            .subscribe((res: HttpResponse<Customer[]>) => { this.customers = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postOfficeService.query()
            .subscribe((res: HttpResponse<PostOffice[]>) => { this.postoffices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
                this.customerPostOffice.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        if (this.customerPostOffice.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerPostOfficeService.update(this.customerPostOffice));
        } else {
            this.subscribeToSaveResponse(
                this.customerPostOfficeService.create(this.customerPostOffice));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CustomerPostOffice>>) {
        result.subscribe((res: HttpResponse<CustomerPostOffice>) =>
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
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCustomerById(index: number, item: Customer) {
        return item.id;
    }

    trackCompanyById(index: number, item: Company) {
        return item.id;
    }

    trackPostOfficeById(index: number, item: PostOffice) {
        return item.id;
    }
    get customerPostOffice() {
        return this._customerPostOffice;
    }

    set customerPostOffice(customerPostOffice: CustomerPostOffice) {
        this._customerPostOffice = customerPostOffice;
        this.createDate = moment(customerPostOffice.createDate).format(DATE_TIME_FORMAT);
    }
}
