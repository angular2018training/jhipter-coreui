import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationCod } from '../../shared/model/quotation-cod.model';
import { QuotationCodService } from '../../shared/service/quotation-cod.service';

@Component({
    selector: 'jhi-quotation-cod-detail',
    templateUrl: './quotation-cod-detail.component.html'
})
export class QuotationCodDetailComponent implements OnInit, OnDestroy {

    quotationCod: QuotationCod;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private quotationCodService: QuotationCodService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInQuotationCods();
    }

    load(id) {
        this.quotationCodService.find(id)
            .subscribe((quotationCodResponse: HttpResponse<QuotationCod>) => {
                this.quotationCod = quotationCodResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInQuotationCods() {
        this.eventSubscriber = this.eventManager.subscribe(
            'quotationCodListModification',
            (response) => this.load(this.quotationCod.id)
        );
    }
}
