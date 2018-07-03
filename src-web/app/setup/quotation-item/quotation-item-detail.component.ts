import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { QuotationItem } from '../../shared/model/quotation-item.model';
import { QuotationItemService } from '../../shared/service/quotation-item.service';

@Component({
    selector: 'jhi-quotation-item-detail',
    templateUrl: './quotation-item-detail.component.html'
})
export class QuotationItemDetailComponent implements OnInit, OnDestroy {

    quotationItem: QuotationItem;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private quotationItemService: QuotationItemService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInQuotationItems();
    }

    load(id) {
        this.quotationItemService.find(id)
            .subscribe((quotationItemResponse: HttpResponse<QuotationItem>) => {
                this.quotationItem = quotationItemResponse.body;
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

    registerChangeInQuotationItems() {
        this.eventSubscriber = this.eventManager.subscribe(
            'quotationItemListModification',
            (response) => this.load(this.quotationItem.id)
        );
    }
}
