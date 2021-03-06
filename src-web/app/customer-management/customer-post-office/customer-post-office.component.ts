import { Component, OnInit, OnDestroy, ViewChild, Input } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { NgForm } from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { CustomerPostOfficePopupService } from './customer-post-office-popup.service';
import { CustomerPostOfficeDeleteDialogComponent } from './customer-post-office-delete-dialog.component';
import { Principal } from '../../shared';
import { PostOffice } from '../../shared/model/post-office.model';
import { PostOfficeService } from '../../setup/post-office/post-office.service';
import { CustomerPostOfficeDialogComponent } from './customer-post-office-dialog.component';
import { CustomerPostOffice } from '../../shared/model/customer-post-office.model';
import { CustomerPostOfficeService } from '../../shared/service/customer-post-office.service';

@Component({
    selector: 'jhi-customer-post-office',
    templateUrl: './customer-post-office.component.html',
    providers: [CustomerPostOfficePopupService]
})
export class CustomerPostOfficeComponent implements OnInit, OnDestroy {

    @Input() customerId: number;

    currentAccount: any;
    customerPostOffices: CustomerPostOffice[];
    error: any;
    success: any;
    eventSubscriber: Subscription;
    currentSearch: string;
    routeData: any;
    links: any;
    totalItems: any;
    queryCount: any;

    predicate: any;
    previousPage: any;
    reverse: any;

    postOffices: PostOffice[];
    @ViewChild(NgForm) searchForm: NgForm;

    constructor(
        private customerPostOfficeService: CustomerPostOfficeService,
        private principal: Principal,
        private eventManager: JhiEventManager,
        private postOfficeService: PostOfficeService,
        private customerPostOfficePopupService: CustomerPostOfficePopupService,
        private alertService: JhiAlertService
    ) {
        this.postOffices = [];
    }

    loadAll() {
        const obj = {
            'customerParentId.equals': this.customerId,
            size: 10000
        };
        this.customerPostOfficeService.query(obj).subscribe(
            (res: HttpResponse<CustomerPostOffice[]>) => this.onSuccess(res.body, res.headers),
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {

        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCustomerPostOffices();

        this.postOfficeService.query().subscribe(
            (res: HttpResponse<PostOffice[]>) => this.postOffices = res.body,
            (res: HttpErrorResponse) => this.onError(res.message)
        );

        this.loadAll();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CustomerPostOffice) {
        return item.id;
    }

    registerChangeInCustomerPostOffices() {
        this.eventSubscriber = this.eventManager.subscribe('customerPostOfficeListModification', (response) => this.loadAll());
    }

    showPostOffice(id) {
        this.customerPostOfficePopupService.open(CustomerPostOfficeDialogComponent as Component, id, this.customerId);
    }

    private onSuccess(data, headers) {
        this.customerPostOffices = data;
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id: number) {
        this.customerPostOfficePopupService
            .open(CustomerPostOfficeDeleteDialogComponent as Component, id);
    }
}
