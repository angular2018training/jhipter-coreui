import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { OrderUserRouteType } from './order-user-route-type.model';
import { OrderUserRouteTypeService } from './order-user-route-type.service';

@Component({
    selector: 'jhi-order-user-route-type-detail',
    templateUrl: './order-user-route-type-detail.component.html'
})
export class OrderUserRouteTypeDetailComponent implements OnInit, OnDestroy {

    orderUserRouteType: OrderUserRouteType;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private orderUserRouteTypeService: OrderUserRouteTypeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInOrderUserRouteTypes();
    }

    load(id) {
        this.orderUserRouteTypeService.find(id)
            .subscribe((orderUserRouteTypeResponse: HttpResponse<OrderUserRouteType>) => {
                this.orderUserRouteType = orderUserRouteTypeResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInOrderUserRouteTypes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'orderUserRouteTypeListModification',
            (response) => this.load(this.orderUserRouteType.id)
        );
    }
}
