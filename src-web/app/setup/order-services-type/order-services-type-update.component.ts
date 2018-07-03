
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';

import { OrderServicesTypeService } from './order-services-type.service';

import { OrderServicesType } from './order-services-type.model';

@Component({
    selector: 'jhi-order-services-type-update',
    templateUrl: './order-services-type-update.component.html'
})
export class OrderServicesTypeUpdateComponent implements OnInit {

    private _orderServicesType: OrderServicesType;
    isSaving: boolean;
    private currentAccount : any;

    constructor(
        private orderServicesTypeService: OrderServicesTypeService,
        private principal : Principal,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({orderServicesType}) => {
            this.orderServicesType = orderServicesType;
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
        if (this.orderServicesType.id !== undefined) {
            this.subscribeToSaveResponse(
                this.orderServicesTypeService.update(this.orderServicesType));
        } else {
            this.subscribeToSaveResponse(
                this.orderServicesTypeService.create(this.orderServicesType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<OrderServicesType>>) {
        result.subscribe((res: HttpResponse<OrderServicesType>) =>
            this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get orderServicesType() {
        return this._orderServicesType;
    }

    set orderServicesType(orderServicesType: OrderServicesType) {
        this._orderServicesType = orderServicesType;
    }
}
