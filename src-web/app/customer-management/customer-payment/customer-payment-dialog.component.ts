import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CustomerPayment } from './customer-payment.model';
import { CustomerPaymentPopupService } from './customer-payment-popup.service';
import { CustomerPaymentService } from './customer-payment.service';
import { Bank, BankService } from '../bank';
import { UserExtraInfo, UserExtraInfoService } from '../user-extra-info';

@Component({
    selector: 'jhi-customer-payment-dialog',
    templateUrl: './customer-payment-dialog.component.html'
})
export class CustomerPaymentDialogComponent implements OnInit {

    customerPayment: CustomerPayment;
    isSaving: boolean;

    banks: Bank[];

    userextrainfos: UserExtraInfo[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private customerPaymentService: CustomerPaymentService,
        private bankService: BankService,
        private userExtraInfoService: UserExtraInfoService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.bankService.query()
            .subscribe((res: HttpResponse<Bank[]>) => { this.banks = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.userExtraInfoService.query()
            .subscribe((res: HttpResponse<UserExtraInfo[]>) => { this.userextrainfos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
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
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CustomerPayment) {
        this.eventManager.broadcast({ name: 'customerPaymentListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackBankById(index: number, item: Bank) {
        return item.id;
    }

    trackUserExtraInfoById(index: number, item: UserExtraInfo) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-customer-payment-popup',
    template: ''
})
export class CustomerPaymentPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerPaymentPopupService: CustomerPaymentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.customerPaymentPopupService
                    .open(CustomerPaymentDialogComponent as Component, params['id']);
            } else {
                this.customerPaymentPopupService
                    .open(CustomerPaymentDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
