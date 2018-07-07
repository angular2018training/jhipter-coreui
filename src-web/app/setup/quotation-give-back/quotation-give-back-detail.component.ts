import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationGiveBack } from '../../shared/model/quotation-give-back.model';
import { QuotationGiveBackService } from '../../shared/service/quotation-give-back.service';

@Component({
    selector: 'jhi-quotation-give-back-detail',
    templateUrl: './quotation-give-back-detail.component.html'
})
export class QuotationGiveBackDetailComponent implements OnInit, OnDestroy {

    quotationGiveBack: QuotationGiveBack;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private quotationGiveBackService: QuotationGiveBackService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInQuotationGiveBacks();
    }

    load(id) {
        this.quotationGiveBackService.find(id)
            .subscribe((quotationGiveBackResponse: HttpResponse<QuotationGiveBack>) => {
                this.quotationGiveBack = quotationGiveBackResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInQuotationGiveBacks() {
        this.eventSubscriber = this.eventManager.subscribe(
            'quotationGiveBackListModification',
            (response) => this.load(this.quotationGiveBack.id)
        );
    }
}
