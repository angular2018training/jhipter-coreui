
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { OrderSubServicesService } from './order-sub-services.service';

import { OrderSubServices } from './order-sub-services.model';
            import { Company, CompanyService } from '../company';

@Component({
    selector: 'jhi-order-sub-services-update',
    templateUrl: './order-sub-services-update.component.html'
})
export class OrderSubServicesUpdateComponent implements OnInit {

    private _orderSubServices: OrderSubServices;
    isSaving: boolean;
    private currentAccount : any;

    constructor(
        private alertService: AlertService,
        private orderSubServicesService: OrderSubServicesService,
        private principal : Principal,
        private companyService: CompanyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({orderSubServices}) => {
            this.orderSubServices = orderSubServices;
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
        if (this.orderSubServices.id !== undefined) {
            this.subscribeToSaveResponse(
                this.orderSubServicesService.update(this.orderSubServices));
        } else {
            this.orderSubServices.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.orderSubServicesService.create(this.orderSubServices));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<OrderSubServices>>) {
        result.subscribe((res: HttpResponse<OrderSubServices>) =>
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
    get orderSubServices() {
        return this._orderSubServices;
    }

    set orderSubServices(orderSubServices: OrderSubServices) {
        this._orderSubServices = orderSubServices;
    }
}
