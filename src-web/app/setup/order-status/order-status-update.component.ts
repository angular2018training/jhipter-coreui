
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { OrderStatusService } from './order-status.service';

import { OrderStatus } from './order-status.model';
            import { Company, CompanyService } from '../company';

@Component({
    selector: 'jhi-order-status-update',
    templateUrl: './order-status-update.component.html'
})
export class OrderStatusUpdateComponent implements OnInit {

    private _orderStatus: OrderStatus;
    isSaving: boolean;
    private currentAccount : any;

    constructor(
        private alertService: AlertService,
        private orderStatusService: OrderStatusService,
        private principal : Principal,
        private companyService: CompanyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({orderStatus}) => {
            this.orderStatus = orderStatus;
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
        if (this.orderStatus.id !== undefined) {
            this.subscribeToSaveResponse(
                this.orderStatusService.update(this.orderStatus));
        } else {
            this.orderStatus.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.orderStatusService.create(this.orderStatus));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<OrderStatus>>) {
        result.subscribe((res: HttpResponse<OrderStatus>) =>
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
    get orderStatus() {
        return this._orderStatus;
    }

    set orderStatus(orderStatus: OrderStatus) {
        this._orderStatus = orderStatus;
    }
}
