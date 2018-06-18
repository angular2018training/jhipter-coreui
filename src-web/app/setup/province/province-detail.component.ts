import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Province } from './province.model';
import { ProvinceService } from './province.service';

@Component({
    selector: 'jhi-province-detail',
    templateUrl: './province-detail.component.html'
})
export class ProvinceDetailComponent implements OnInit, OnDestroy {

    province: Province;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private provinceService: ProvinceService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInProvinces();
    }

    load(id) {
        this.provinceService.find(id)
            .subscribe((provinceResponse: HttpResponse<Province>) => {
                this.province = provinceResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInProvinces() {
        this.eventSubscriber = this.eventManager.subscribe(
            'provinceListModification',
            (response) => this.load(this.province.id)
        );
    }
}
