import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { Customer } from './customer.model';
import { CustomerPopupService } from './customer-popup.service';
import { CustomerService } from './customer.service';
import { CustomerLegal, CustomerLegalService } from '../customer-legal';
import { CustomerPayment, CustomerPaymentService } from '../customer-payment';
import { UserExtraInfo, UserExtraInfoService } from '../user-extra-info';
import { Province, ProvinceService } from '../province';
import { District, DistrictService } from '../district';

@Component({
    selector: 'jhi-customer-dialog',
    templateUrl: './customer-dialog.component.html'
})
export class CustomerDialogComponent implements OnInit {

    customer: Customer;
    isSaving: boolean;

    legals: CustomerLegal[];

    payments: CustomerPayment[];

    userextrainfos: UserExtraInfo[];

    provinces: Province[];

    districts: District[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private customerService: CustomerService,
        private customerLegalService: CustomerLegalService,
        private customerPaymentService: CustomerPaymentService,
        private userExtraInfoService: UserExtraInfoService,
        private provinceService: ProvinceService,
        private districtService: DistrictService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.customerLegalService
            .query({filter: 'customer-is-null'})
            .subscribe((res: HttpResponse<CustomerLegal[]>) => {
                if (!this.customer.legalId) {
                    this.legals = res.body;
                } else {
                    this.customerLegalService
                        .find(this.customer.legalId)
                        .subscribe((subRes: HttpResponse<CustomerLegal>) => {
                            this.legals = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.customerPaymentService
            .query({filter: 'customer-is-null'})
            .subscribe((res: HttpResponse<CustomerPayment[]>) => {
                if (!this.customer.paymentId) {
                    this.payments = res.body;
                } else {
                    this.customerPaymentService
                        .find(this.customer.paymentId)
                        .subscribe((subRes: HttpResponse<CustomerPayment>) => {
                            this.payments = [subRes.body].concat(res.body);
                        }, (subRes: HttpErrorResponse) => this.onError(subRes.message));
                }
            }, (res: HttpErrorResponse) => this.onError(res.message));
        this.userExtraInfoService.query()
            .subscribe((res: HttpResponse<UserExtraInfo[]>) => { this.userextrainfos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.provinceService.query()
            .subscribe((res: HttpResponse<Province[]>) => { this.provinces = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.districtService.query()
            .subscribe((res: HttpResponse<District[]>) => { this.districts = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.customer.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerService.update(this.customer));
        } else {
            this.subscribeToSaveResponse(
                this.customerService.create(this.customer));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Customer>>) {
        result.subscribe((res: HttpResponse<Customer>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: Customer) {
        this.eventManager.broadcast({ name: 'customerListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackCustomerLegalById(index: number, item: CustomerLegal) {
        return item.id;
    }

    trackCustomerPaymentById(index: number, item: CustomerPayment) {
        return item.id;
    }

    trackUserExtraInfoById(index: number, item: UserExtraInfo) {
        return item.id;
    }

    trackProvinceById(index: number, item: Province) {
        return item.id;
    }

    trackDistrictById(index: number, item: District) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-customer-popup',
    template: ''
})
export class CustomerPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerPopupService: CustomerPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.customerPopupService
                    .open(CustomerDialogComponent as Component, params['id']);
            } else {
                this.customerPopupService
                    .open(CustomerDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
