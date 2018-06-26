import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { CustomerPayment } from './customer-payment.model';
import { CustomerPaymentService } from './customer-payment.service';

@Component({
    selector: 'jhi-customer-payment-detail',
    templateUrl: './customer-payment-detail.component.html'
})
export class CustomerPaymentDetailComponent implements OnInit, OnDestroy {

    customerPayment: CustomerPayment;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private customerPaymentService: CustomerPaymentService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCustomerPayments();
    }

    load(id) {
        this.customerPaymentService.find(id)
            .subscribe((customerPaymentResponse: HttpResponse<CustomerPayment>) => {
                this.customerPayment = customerPaymentResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCustomerPayments() {
        this.eventSubscriber = this.eventManager.subscribe(
            'customerPaymentListModification',
            (response) => this.load(this.customerPayment.id)
        );
    }
}
