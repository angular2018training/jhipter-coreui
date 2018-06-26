import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { UserPosition } from './user-position.model';
import { UserPositionService } from './user-position.service';

@Component({
    selector: 'jhi-user-position-detail',
    templateUrl: './user-position-detail.component.html'
})
export class UserPositionDetailComponent implements OnInit, OnDestroy {

    userPosition: UserPosition;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private userPositionService: UserPositionService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInUserPositions();
    }

    load(id) {
        this.userPositionService.find(id)
            .subscribe((userPositionResponse: HttpResponse<UserPosition>) => {
                this.userPosition = userPositionResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInUserPositions() {
        this.eventSubscriber = this.eventManager.subscribe(
            'userPositionListModification',
            (response) => this.load(this.userPosition.id)
        );
    }
}
