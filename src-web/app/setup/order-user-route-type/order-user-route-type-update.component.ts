
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { OrderUserRouteTypeService } from './order-user-route-type.service';

import { OrderUserRouteType } from './order-user-route-type.model';
            import { Company, CompanyService } from '../company';

@Component({
    selector: 'jhi-order-user-route-type-update',
    templateUrl: './order-user-route-type-update.component.html'
})
export class OrderUserRouteTypeUpdateComponent implements OnInit {

    private _orderUserRouteType: OrderUserRouteType;
    isSaving: boolean;
    private currentAccount : any;

    constructor(
        private alertService: AlertService,
        private orderUserRouteTypeService: OrderUserRouteTypeService,
        private principal : Principal,
        private companyService: CompanyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({orderUserRouteType}) => {
            this.orderUserRouteType = orderUserRouteType;
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
        if (this.orderUserRouteType.id !== undefined) {
            this.subscribeToSaveResponse(
                this.orderUserRouteTypeService.update(this.orderUserRouteType));
        } else {
            this.orderUserRouteType.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.orderUserRouteTypeService.create(this.orderUserRouteType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<OrderUserRouteType>>) {
        result.subscribe((res: HttpResponse<OrderUserRouteType>) =>
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
    get orderUserRouteType() {
        return this._orderUserRouteType;
    }

    set orderUserRouteType(orderUserRouteType: OrderUserRouteType) {
        this._orderUserRouteType = orderUserRouteType;
    }
}
