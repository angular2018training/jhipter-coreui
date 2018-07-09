import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { DistrictType } from './district-type.model';
import { DistrictTypeService } from './district-type.service';

@Component({
    selector: 'jhi-district-type-detail',
    templateUrl: './district-type-detail.component.html'
})
export class DistrictTypeDetailComponent implements OnInit, OnDestroy {

    districtType: DistrictType;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private districtTypeService: DistrictTypeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInDistrictTypes();
    }

    load(id) {
        this.districtTypeService.find(id)
            .subscribe((districtTypeResponse: HttpResponse<DistrictType>) => {
                this.districtType = districtTypeResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInDistrictTypes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'districtTypeListModification',
            (response) => this.load(this.districtType.id)
        );
    }
}
