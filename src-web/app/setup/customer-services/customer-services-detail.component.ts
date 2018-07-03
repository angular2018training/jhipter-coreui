import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { CustomerServices } from './customer-services.model';
import { CustomerServicesService } from './customer-services.service';

@Component({
    selector: 'jhi-customer-services-detail',
    templateUrl: './customer-services-detail.component.html'
})
export class CustomerServicesDetailComponent implements OnInit, OnDestroy {

    customerServices: CustomerServices;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private customerServicesService: CustomerServicesService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCustomerServices();
    }

    load(id) {
        this.customerServicesService.find(id)
            .subscribe((customerServicesResponse: HttpResponse<CustomerServices>) => {
                this.customerServices = customerServicesResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCustomerServices() {
        this.eventSubscriber = this.eventManager.subscribe(
            'customerServicesListModification',
            (response) => this.load(this.customerServices.id)
        );
    }
}
