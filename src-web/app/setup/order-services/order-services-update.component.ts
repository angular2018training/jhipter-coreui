
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { OrderServicesService } from '../../shared/service/order-services.service';

import { OrderServices } from '../../shared/model/order-services.model';
            import { Company } from '../../shared/model/company.model';
            import { CompanyService} from '../../shared/service/company.service';
            import { OrderServicesType } from '../../shared/model/order-services-type.model';
            import { OrderServicesTypeService} from '../../shared/service/order-services-type.service';
            import { Quotation } from '../../shared/model/quotation.model';
            import { QuotationService} from '../../shared/service/quotation.service';

@Component({
    selector: 'jhi-order-services-update',
    templateUrl: './order-services-update.component.html'
})
export class OrderServicesUpdateComponent implements OnInit {

    private _orderServices: OrderServices;
    isSaving: boolean;
    private currentAccount : any;

    orderservicestypes: OrderServicesType[];

    quotations: Quotation[];

    constructor(
        private alertService: AlertService,
        private orderServicesService: OrderServicesService,
        private principal : Principal,
        private companyService: CompanyService,
        private orderServicesTypeService: OrderServicesTypeService,
        private quotationService: QuotationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({orderServices}) => {
            this.orderServices = orderServices;
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.quotationService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<Quotation[]>) => { this.quotations = res.body;}, (res: HttpErrorResponse) => this.onError(res.message));
        });
        this.orderServicesTypeService.query()
            .subscribe((res: HttpResponse<OrderServicesType[]>) => { this.orderservicestypes = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));

    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.orderServices.id !== undefined) {
            this.subscribeToSaveResponse(
                this.orderServicesService.update(this.orderServices));
        } else {
            this.orderServices.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.orderServicesService.create(this.orderServices));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<OrderServices>>) {
        result.subscribe((res: HttpResponse<OrderServices>) =>
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

    trackOrderServicesTypeById(index: number, item: OrderServicesType) {
        return item.id;
    }

    trackQuotationById(index: number, item: Quotation) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
    get orderServices() {
        return this._orderServices;
    }

    set orderServices(orderServices: OrderServices) {
        this._orderServices = orderServices;
    }
}
