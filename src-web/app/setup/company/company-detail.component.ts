import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Company } from './company.model';
import { CompanyService } from './company.service';

@Component({
    selector: 'jhi-company-detail',
    templateUrl: './company-detail.component.html'
})
export class CompanyDetailComponent implements OnInit, OnDestroy {

    company: Company;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private companyService: CompanyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInCompanies();
    }

    load(id) {
        this.companyService.find(id)
            .subscribe((companyResponse: HttpResponse<Company>) => {
                this.company = companyResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInCompanies() {
        this.eventSubscriber = this.eventManager.subscribe(
            'companyListModification',
            (response) => this.load(this.company.id)
        );
    }
}
