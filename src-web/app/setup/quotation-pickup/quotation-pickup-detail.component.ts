import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationPickup } from '../../shared/model/quotation-pickup.model';
import { QuotationPickupService } from '../../shared/service/quotation-pickup.service';

@Component({
    selector: 'jhi-quotation-pickup-detail',
    templateUrl: './quotation-pickup-detail.component.html'
})
export class QuotationPickupDetailComponent implements OnInit, OnDestroy {

    quotationPickup: QuotationPickup;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private quotationPickupService: QuotationPickupService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInQuotationPickups();
    }

    load(id) {
        this.quotationPickupService.find(id)
            .subscribe((quotationPickupResponse: HttpResponse<QuotationPickup>) => {
                this.quotationPickup = quotationPickupResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInQuotationPickups() {
        this.eventSubscriber = this.eventManager.subscribe(
            'quotationPickupListModification',
            (response) => this.load(this.quotationPickup.id)
        );
    }
}
