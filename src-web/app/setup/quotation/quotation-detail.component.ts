import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Quotation } from '../../shared/model/quotation.model';
import { QuotationService } from '../../shared/service/quotation.service';

@Component({
    selector: 'jhi-quotation-detail',
    templateUrl: './quotation-detail.component.html'
})
export class QuotationDetailComponent implements OnInit, OnDestroy {

    quotation: Quotation;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private quotationService: QuotationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInQuotations();
    }

    load(id) {
        this.quotationService.find(id)
            .subscribe((quotationResponse: HttpResponse<Quotation>) => {
                this.quotation = quotationResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInQuotations() {
        this.eventSubscriber = this.eventManager.subscribe(
            'quotationListModification',
            (response) => this.load(this.quotation.id)
        );
    }
}
