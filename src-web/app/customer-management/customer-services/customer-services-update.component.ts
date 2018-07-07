
import { Component, OnInit } from '@angular/core';
import { Principal } from '../../shared/';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { ITEMS_QUERY_ALL } from '../../shared/';
import { AlertService } from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { Quotation } from '../../shared/model/quotation.model';
import { CustomerServices } from '../../shared/model/customer-services.model';
import { Customer } from '../../shared/model/customer.model';
import { CustomerServicesService } from '../../shared/model/customer-services.service';
import { QuotationService } from '../../shared/service/quotation.service';
import { OrderServicesService } from '../../shared/service/order-services.service';
import { OrderServices } from '../../shared/model/order-services.model';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

@Component({
    selector: 'jhi-customer-services-update',
    templateUrl: './customer-services-update.component.html'
})
export class CustomerServicesUpdateComponent implements OnInit {

    customerServices: CustomerServices;

    isSaving: boolean;

    private currentAccount: any;

    orderservices: OrderServices[];

    quotations: Quotation[];

    customers: Customer[];

    constructor(
        private alertService: AlertService,
        public activeModal: NgbActiveModal,
        private customerServicesService: CustomerServicesService,
        private principal: Principal,
        private orderServiceService: OrderServicesService,
        private quotationService: QuotationService,
        private eventManager: JhiEventManager
    ) { }

    ngOnInit() {
        this.isSaving = false;

        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.orderServiceService.query({
                'companyId.equals': this.currentAccount.companyId,
                'pageSize': ITEMS_QUERY_ALL
            }).subscribe(
                (res: HttpResponse<OrderServices[]>) => { this.orderservices = res.body; },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.quotationService.query({
                'companyId.equals': this.currentAccount.companyId,
                'pageSize': ITEMS_QUERY_ALL
            }).subscribe(
                (res: HttpResponse<Quotation[]>) => { this.quotations = res.body; },
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });

    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.customerServices.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerServicesService.update(this.customerServices));
        } else {
            this.subscribeToSaveResponse(
                this.customerServicesService.create(this.customerServices));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CustomerServices>>) {
        result.subscribe((res: HttpResponse<CustomerServices>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CustomerServices) {
        this.eventManager.broadcast({ name: 'customer-services-detail.save.success', content: 'OK' });
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
    }
}
