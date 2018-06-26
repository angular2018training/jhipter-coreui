import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { CustomerLegal } from './customer-legal.model';
import { CustomerLegalService } from './customer-legal.service';

@Component({
    selector: 'jhi-customer-legal-detail',
    templateUrl: './customer-legal-detail.component.html'
})
export class CustomerLegalDetailComponent implements OnInit, OnDestroy {

    customerLegal: CustomerLegal;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private customerLegalService: CustomerLegalService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCustomerLegals();
    }

    load(id) {
        this.customerLegalService.find(id)
            .subscribe((customerLegalResponse: HttpResponse<CustomerLegal>) => {
                this.customerLegal = customerLegalResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCustomerLegals() {
        this.eventSubscriber = this.eventManager.subscribe(
            'customerLegalListModification',
            (response) => this.load(this.customerLegal.id)
        );
    }
}
