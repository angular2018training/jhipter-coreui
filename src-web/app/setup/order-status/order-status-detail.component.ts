import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { OrderStatus } from './order-status.model';
import { OrderStatusService } from './order-status.service';

@Component({
    selector: 'jhi-order-status-detail',
    templateUrl: './order-status-detail.component.html'
})
export class OrderStatusDetailComponent implements OnInit, OnDestroy {

    orderStatus: OrderStatus;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private orderStatusService: OrderStatusService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInOrderStatuses();
    }

    load(id) {
        this.orderStatusService.find(id)
            .subscribe((orderStatusResponse: HttpResponse<OrderStatus>) => {
                this.orderStatus = orderStatusResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInOrderStatuses() {
        this.eventSubscriber = this.eventManager.subscribe(
            'orderStatusListModification',
            (response) => this.load(this.orderStatus.id)
        );
    }
}
