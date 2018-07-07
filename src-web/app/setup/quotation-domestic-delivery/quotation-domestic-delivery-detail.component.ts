import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationDomesticDelivery } from '../../shared/model/quotation-domestic-delivery.model';
import { QuotationDomesticDeliveryService } from '../../shared/service/quotation-domestic-delivery.service';

@Component({
    selector: 'jhi-quotation-domestic-delivery-detail',
    templateUrl: './quotation-domestic-delivery-detail.component.html'
})
export class QuotationDomesticDeliveryDetailComponent implements OnInit, OnDestroy {

    quotationDomesticDelivery: QuotationDomesticDelivery;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private quotationDomesticDeliveryService: QuotationDomesticDeliveryService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInQuotationDomesticDeliveries();
    }

    load(id) {
        this.quotationDomesticDeliveryService.find(id)
            .subscribe((quotationDomesticDeliveryResponse: HttpResponse<QuotationDomesticDelivery>) => {
                this.quotationDomesticDelivery = quotationDomesticDeliveryResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInQuotationDomesticDeliveries() {
        this.eventSubscriber = this.eventManager.subscribe(
            'quotationDomesticDeliveryListModification',
            (response) => this.load(this.quotationDomesticDelivery.id)
        );
    }
}
