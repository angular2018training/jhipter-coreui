import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { CustomerPostOffice } from './customer-post-office.model';
import { CustomerPostOfficeService } from './customer-post-office.service';

@Component({
    selector: 'jhi-customer-post-office-detail',
    templateUrl: './customer-post-office-detail.component.html'
})
export class CustomerPostOfficeDetailComponent implements OnInit, OnDestroy {

    customerPostOffice: CustomerPostOffice;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private customerPostOfficeService: CustomerPostOfficeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCustomerPostOffices();
    }

    load(id) {
        this.customerPostOfficeService.find(id)
            .subscribe((customerPostOfficeResponse: HttpResponse<CustomerPostOffice>) => {
                this.customerPostOffice = customerPostOfficeResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCustomerPostOffices() {
        this.eventSubscriber = this.eventManager.subscribe(
            'customerPostOfficeListModification',
            (response) => this.load(this.customerPostOffice.id)
        );
    }
}
