import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { CustomerSource } from './customer-source.model';
import { CustomerSourceService } from './customer-source.service';

@Component({
    selector: 'jhi-customer-source-detail',
    templateUrl: './customer-source-detail.component.html'
})
export class CustomerSourceDetailComponent implements OnInit, OnDestroy {

    customerSource: CustomerSource;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private customerSourceService: CustomerSourceService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCustomerSources();
    }

    load(id) {
        this.customerSourceService.find(id)
            .subscribe((customerSourceResponse: HttpResponse<CustomerSource>) => {
                this.customerSource = customerSourceResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCustomerSources() {
        this.eventSubscriber = this.eventManager.subscribe(
            'customerSourceListModification',
            (response) => this.load(this.customerSource.id)
        );
    }
}
