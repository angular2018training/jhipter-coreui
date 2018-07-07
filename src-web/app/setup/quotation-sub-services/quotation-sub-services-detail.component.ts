import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationSubServices } from '../../shared/model/quotation-sub-services.model';
import { QuotationSubServicesService } from '../../shared/service/quotation-sub-services.service';

@Component({
    selector: 'jhi-quotation-sub-services-detail',
    templateUrl: './quotation-sub-services-detail.component.html'
})
export class QuotationSubServicesDetailComponent implements OnInit, OnDestroy {

    quotationSubServices: QuotationSubServices;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private quotationSubServicesService: QuotationSubServicesService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInQuotationSubServices();
    }

    load(id) {
        this.quotationSubServicesService.find(id)
            .subscribe((quotationSubServicesResponse: HttpResponse<QuotationSubServices>) => {
                this.quotationSubServices = quotationSubServicesResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInQuotationSubServices() {
        this.eventSubscriber = this.eventManager.subscribe(
            'quotationSubServicesListModification',
            (response) => this.load(this.quotationSubServices.id)
        );
    }
}
