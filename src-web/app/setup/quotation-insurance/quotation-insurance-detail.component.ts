import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationInsurance } from '../../shared/model/quotation-insurance.model';
import { QuotationInsuranceService } from '../../shared/service/quotation-insurance.service';

@Component({
    selector: 'jhi-quotation-insurance-detail',
    templateUrl: './quotation-insurance-detail.component.html'
})
export class QuotationInsuranceDetailComponent implements OnInit, OnDestroy {

    quotationInsurance: QuotationInsurance;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private quotationInsuranceService: QuotationInsuranceService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInQuotationInsurances();
    }

    load(id) {
        this.quotationInsuranceService.find(id)
            .subscribe((quotationInsuranceResponse: HttpResponse<QuotationInsurance>) => {
                this.quotationInsurance = quotationInsuranceResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInQuotationInsurances() {
        this.eventSubscriber = this.eventManager.subscribe(
            'quotationInsuranceListModification',
            (response) => this.load(this.quotationInsurance.id)
        );
    }
}
