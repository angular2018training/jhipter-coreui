import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Ward } from './ward.model';
import { WardService } from './ward.service';

@Component({
    selector: 'jhi-ward-detail',
    templateUrl: './ward-detail.component.html'
})
export class WardDetailComponent implements OnInit, OnDestroy {

    ward: Ward;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private wardService: WardService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInWards();
    }

    load(id) {
        this.wardService.find(id)
            .subscribe((wardResponse: HttpResponse<Ward>) => {
                this.ward = wardResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInWards() {
        this.eventSubscriber = this.eventManager.subscribe(
            'wardListModification',
            (response) => this.load(this.ward.id)
        );
    }
}
