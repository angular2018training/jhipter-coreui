import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from '../../shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils, JhiEventManager } from 'ng-jhipster';

import { CustomerPaymentService } from './customer-payment.service';

import { CustomerPayment } from './customer-payment.model';
import { Company } from '../../shared/model/company.model';
import { Bank } from '../../shared/model/bank.model';
import { UserExtraInfo } from '../../shared/model/user-extra-info.model';
import { PaymentType } from '../../setup/payment-type';
import { CompanyService } from '../../shared/service/company.service';
import { BankService } from '../../setup/bank';
import { UserExtraInfoService } from '../../shared/service/user-extra-info.service';
import { PaymentTypeService } from '../../shared/service/payment-type.service';

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
    customerId: number;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private customerPaymentService: CustomerPaymentService,
        private companyService: CompanyService,
        private bankService: BankService,
        private userExtraInfoService: UserExtraInfoService,
        private paymentTypeService: PaymentTypeService,
        private route: ActivatedRoute,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe((data) => {
            this.customerPayment = data.resolved.customerPayment;
            this.customerId = data.resolved.customerId;
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

    save(isVerified = false) {
        this.isSaving = true;
        this.customerPayment.dateVerify = moment(this.dateVerify, DATE_TIME_FORMAT);
        this.customerPayment.isVerify = isVerified;
        if (this.customerPayment.id) {
            this.subscribeToSaveResponse(
                this.customerPaymentService.update(this.customerPayment, this.customerId));
        } else {
            this.customerPaymentService.create(this.customerPayment, this.customerId).subscribe((res: HttpResponse<CustomerPayment>) => {
                this.isSaving = false;
                if (res.body) {
                    this.customerPayment.id = res.body.id;
                    this.eventManager.broadcast({
                        name: 'update-current-customer',
                        customer: {
                            paymentId: this.customerPayment.id
                        }
                    });
                }
            }, (res: HttpErrorResponse) => this.onSaveError());
        }
    }

    verifyData() {
        this.dateVerify = moment().format(DATE_TIME_FORMAT);
        this.customerPayment.isVerify = true;
        this.save(true);
    }
    private subscribeToSaveResponse(result: Observable<HttpResponse<CustomerPayment>>) {
        result.subscribe((res: HttpResponse<CustomerPayment>) =>
            this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(res) {
        this.isSaving = false;
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
        if (customerPayment.dateVerify) {
            this.dateVerify = moment(customerPayment.dateVerify).format(DATE_TIME_FORMAT);
        }
    }
}
