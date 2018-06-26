import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from '../../shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { CustomerPaymentService } from './customer-payment.service';

import { CustomerPayment } from './customer-payment.model';
            import { Company, CompanyService } from '../../setup/company';
            import { Bank, BankService } from '../../setup/bank';
            import { UserExtraInfo, UserExtraInfoService } from '../../setup/user-extra-info';
            import { PaymentType, PaymentTypeService } from '../../setup/payment-type';

@Component({
    selector: 'jhi-customer-payment-update',
    templateUrl: './customer-payment-update.component.html'
})
export class CustomerPaymentUpdateComponent implements OnInit {

    private _customerPayment: CustomerPayment;
    isSaving: boolean;

    companies: Company[];

    banks: Bank[];

    userextrainfos: UserExtraInfo[];

    paymenttypes: PaymentType[];
    dateVerify: string;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private customerPaymentService: CustomerPaymentService,
        private companyService: CompanyService,
        private bankService: BankService,
        private userExtraInfoService: UserExtraInfoService,
        private paymentTypeService: PaymentTypeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({customerPayment}) => {
            this.customerPayment = customerPayment;
        });
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.bankService.query()
            .subscribe((res: HttpResponse<Bank[]>) => { this.banks = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.userExtraInfoService.query()
            .subscribe((res: HttpResponse<UserExtraInfo[]>) => { this.userextrainfos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.paymentTypeService.query()
            .subscribe((res: HttpResponse<PaymentType[]>) => { this.paymenttypes = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
                this.customerPayment.dateVerify = moment(this.dateVerify, DATE_TIME_FORMAT);
        if (this.customerPayment.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerPaymentService.update(this.customerPayment));
        } else {
            this.subscribeToSaveResponse(
                this.customerPaymentService.create(this.customerPayment));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CustomerPayment>>) {
        result.subscribe((res: HttpResponse<CustomerPayment>) =>
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

    trackCompanyById(index: number, item: Company) {
        return item.id;
    }

    trackBankById(index: number, item: Bank) {
        return item.id;
    }

    trackUserExtraInfoById(index: number, item: UserExtraInfo) {
        return item.id;
    }

    trackPaymentTypeById(index: number, item: PaymentType) {
        return item.id;
    }
    get customerPayment() {
        return this._customerPayment;
    }

    set customerPayment(customerPayment: CustomerPayment) {
        this._customerPayment = customerPayment;
        this.dateVerify = moment(customerPayment.dateVerify).format(DATE_TIME_FORMAT);
    }
}
