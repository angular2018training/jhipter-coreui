
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { PaymentTypeService } from './payment-type.service';

import { PaymentType } from './payment-type.model';
            import { Company, CompanyService } from '../company';

@Component({
    selector: 'jhi-payment-type-update',
    templateUrl: './payment-type-update.component.html'
})
export class PaymentTypeUpdateComponent implements OnInit {

    private _paymentType: PaymentType;
    isSaving: boolean;
    private currentAccount : any;

    constructor(
        private alertService: AlertService,
        private paymentTypeService: PaymentTypeService,
        private principal : Principal,
        private companyService: CompanyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({paymentType}) => {
            this.paymentType = paymentType;
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });

    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.paymentType.id !== undefined) {
            this.subscribeToSaveResponse(
                this.paymentTypeService.update(this.paymentType));
        } else {
            this.paymentType.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.paymentTypeService.create(this.paymentType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<PaymentType>>) {
        result.subscribe((res: HttpResponse<PaymentType>) =>
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
    get paymentType() {
        return this._paymentType;
    }

    set paymentType(paymentType: PaymentType) {
        this._paymentType = paymentType;
    }
}
