
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { OrderSubServicesTypeService } from './order-sub-services-type.service';

import { OrderSubServicesType } from './order-sub-services-type.model';
            import { Company, CompanyService } from '../company';

@Component({
    selector: 'jhi-order-sub-services-type-update',
    templateUrl: './order-sub-services-type-update.component.html'
})
export class OrderSubServicesTypeUpdateComponent implements OnInit {

    private _orderSubServicesType: OrderSubServicesType;
    isSaving: boolean;
    private currentAccount : any;

    constructor(
        private alertService: AlertService,
        private orderSubServicesTypeService: OrderSubServicesTypeService,
        private principal : Principal,
        private companyService: CompanyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({orderSubServicesType}) => {
            this.orderSubServicesType = orderSubServicesType;
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
        if (this.orderSubServicesType.id !== undefined) {
            this.subscribeToSaveResponse(
                this.orderSubServicesTypeService.update(this.orderSubServicesType));
        } else {
            this.orderSubServicesType.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.orderSubServicesTypeService.create(this.orderSubServicesType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<OrderSubServicesType>>) {
        result.subscribe((res: HttpResponse<OrderSubServicesType>) =>
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
    get orderSubServicesType() {
        return this._orderSubServicesType;
    }

    set orderSubServicesType(orderSubServicesType: OrderSubServicesType) {
        this._orderSubServicesType = orderSubServicesType;
    }
}
