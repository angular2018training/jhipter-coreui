import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { QuotationSubServices } from '../../shared/model/quotation-sub-services.model';
import { QuotationSubServicesService } from '../../shared/service/quotation-sub-services.service';
import { CompanyService } from '../../shared/service/company.service';
import {Company  } from '../../shared/model/company.model';
import { OrderSubServicesTypeService } from '../../shared/service/order-sub-services-type.service';
import {OrderSubServicesType  } from '../../shared/model/order-sub-services-type.model';
import { OrderSubServicesService } from '../../shared/service/order-sub-services.service';
import {OrderSubServices  } from '../../shared/model/order-sub-services.model';

@Component({
    selector: 'jhi-quotation-sub-services-detail-update',
    templateUrl: './quotation-sub-services-detail-update.component.html'
})
export class QuotationSubServicesDetailUpdateComponent implements OnInit {

    quotationSubServices: QuotationSubServices;
    isSaving: boolean;

    companies: Company[];

    ordersubservicestypes: OrderSubServicesType[];

    ordersubservices: OrderSubServices[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private quotationSubServicesService: QuotationSubServicesService,
        private companyService: CompanyService,
        private orderSubServicesTypeService: OrderSubServicesTypeService,
        private orderSubServicesService: OrderSubServicesService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.orderSubServicesTypeService.query()
            .subscribe((res: HttpResponse<OrderSubServicesType[]>) => { this.ordersubservicestypes = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.orderSubServicesService.query()
            .subscribe((res: HttpResponse<OrderSubServices[]>) => { this.ordersubservices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.quotationSubServices.id !== undefined) {
            this.subscribeToSaveResponse(
                this.quotationSubServicesService.update(this.quotationSubServices));
        } else {
            this.subscribeToSaveResponse(
                this.quotationSubServicesService.create(this.quotationSubServices));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<QuotationSubServices>>) {
        result.subscribe((res: HttpResponse<QuotationSubServices>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: QuotationSubServices) {
        this.eventManager.broadcast({ name: 'quotation-sub-services-detail.save.success', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackCompanyById(index: number, item: Company) {
        return item.id;
    }

    trackOrderSubServicesTypeById(index: number, item: OrderSubServicesType) {
        return item.id;
    }

    trackOrderSubServicesById(index: number, item: OrderSubServices) {
        return item.id;
    }
}

