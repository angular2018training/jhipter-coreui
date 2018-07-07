import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { ActivatedRoute, Router, ActivationEnd } from '@angular/router';
import { NgForm } from '@angular/forms';

import { AlertService } from '../../shared/alert/alert-service';
import { ITEMS_QUERY_ALL, Principal } from '../../shared/';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';

import { CustomerServicesPopupService } from './customer-services-popup.service';
import { OrderServices } from '../../shared/model/order-services.model';
import { Quotation } from '../../shared/model/quotation.model';
import { OrderServicesService } from '../../shared/service/order-services.service';
import { QuotationService } from '../../shared/service/quotation.service';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { CustomerServicesDeleteDialogComponent } from './customer-services-delete-dialog.component';
import { CustomerServicesUpdateComponent } from './customer-services-update.component';
import { CustomerServices } from '../../shared/model/customer-services.model';
import { CustomerServicesService } from '../../shared/service/customer-services.service';

@Component({
    selector: 'jhi-customer-services',
    templateUrl: './customer-services.component.html',
    providers: [CustomerServicesPopupService]
})
export class CustomerServicesComponent implements OnInit, OnDestroy {

    private customerId;

    currentAccount: any;

    eventSubscriber: Subscription;

    orderServices: OrderServices[];

    quotations: Quotation[];

    customerServices: CustomerServices[] = [];

    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private principal: Principal,
        private alertService: AlertService,
        private eventManager: JhiEventManager,
        private orderServiceService: OrderServicesService,
        private quotationService: QuotationService,
        private customerServicesPopupService: CustomerServicesPopupService,
        private activatedRoute: ActivatedRoute,
        private customerServicesService: CustomerServicesService
    ) { }

    ngOnInit() {
        this.activatedRoute.parent.params.subscribe((param) => {
            this.customerId = param.id;
            this.loadCustomerServices();
        });

        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.orderServiceService.query({
                'companyId.equals': this.currentAccount.companyId,
                'pageSize': ITEMS_QUERY_ALL
            }).subscribe(
                (res: HttpResponse<OrderServices[]>) => this.orderServices = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
            this.quotationService.query({
                'companyId.equals': this.currentAccount.companyId,
                'pageSize': ITEMS_QUERY_ALL
            }).subscribe(
                (res: HttpResponse<Quotation[]>) => this.quotations = res.body,
                (res: HttpErrorResponse) => this.onError(res.message)
            );
        });
        this.registerChangeInCustomerServices();
    }

    private loadCustomerServices() {
        this.customerServicesService.query({
            'customerParentId.equals': this.customerId,
            size: 10000
        }).subscribe((res) => this.customerServices = res.body);
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCustomerServices() {
        this.eventSubscriber = this.eventManager.subscribe('customer-services-detail.save.success',
            () => this.loadCustomerServices());
    }

    private onSuccess(data, headers) {
        // this.links = this.parseLinks.parse(headers.get('link'));
        //  this.totalItems = headers.get('X-Total-Count');
        // this.queryCount = this.totalItems;
        // this.page = pagingParams.page;
        // sthis.customerServices = data;
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    deleteItem(id: number) {
        const customerServices = new CustomerServices();
        customerServices.id = id;
        this.customerServicesPopupService
            .open(CustomerServicesDeleteDialogComponent as Component, customerServices);
    }

    showCustomerServiceDetail(id: number) {
        const customerServices = new CustomerServices();
        customerServices.id = id;
        this.customerServicesPopupService.open(CustomerServicesUpdateComponent as Component, customerServices);
    }

    createNewService() {
        const customerServices = new CustomerServices();
        customerServices.companyId = this.currentAccount.companyId;
        customerServices.customerParentId = this.customerId;
        this.customerServicesPopupService.open(CustomerServicesUpdateComponent as Component, customerServices);
    }

    trackId(index: number, item: CustomerServices) {
        return item.id;
    }
}
