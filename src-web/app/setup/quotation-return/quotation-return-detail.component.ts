import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationReturn } from '../../shared/model/quotation-return.model';
import { QuotationReturnService } from '../../shared/service/quotation-return.service';

@Component({
    selector: 'jhi-quotation-return-detail',
    templateUrl: './quotation-return-detail.component.html'
})
export class QuotationReturnDetailComponent implements OnInit, OnDestroy {

    quotationReturn: QuotationReturn;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private quotationReturnService: QuotationReturnService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInQuotationReturns();
    }

    load(id) {
        this.quotationReturnService.find(id)
            .subscribe((quotationReturnResponse: HttpResponse<QuotationReturn>) => {
                this.quotationReturn = quotationReturnResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInQuotationReturns() {
        this.eventSubscriber = this.eventManager.subscribe(
            'quotationReturnListModification',
            (response) => this.load(this.quotationReturn.id)
        );
    }
}
