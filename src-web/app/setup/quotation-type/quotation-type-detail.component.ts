import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationType } from '../../shared/model/quotation-type.model';
import { QuotationTypeService } from '../../shared/service/quotation-type.service';

@Component({
    selector: 'jhi-quotation-type-detail',
    templateUrl: './quotation-type-detail.component.html'
})
export class QuotationTypeDetailComponent implements OnInit, OnDestroy {

    quotationType: QuotationType;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private quotationTypeService: QuotationTypeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInQuotationTypes();
    }

    load(id) {
        this.quotationTypeService.find(id)
            .subscribe((quotationTypeResponse: HttpResponse<QuotationType>) => {
                this.quotationType = quotationTypeResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInQuotationTypes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'quotationTypeListModification',
            (response) => this.load(this.quotationType.id)
        );
    }
}
