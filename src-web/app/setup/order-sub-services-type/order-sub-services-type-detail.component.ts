import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { OrderSubServicesType } from './order-sub-services-type.model';
import { OrderSubServicesTypeService } from './order-sub-services-type.service';

@Component({
    selector: 'jhi-order-sub-services-type-detail',
    templateUrl: './order-sub-services-type-detail.component.html'
})
export class OrderSubServicesTypeDetailComponent implements OnInit, OnDestroy {

    orderSubServicesType: OrderSubServicesType;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private orderSubServicesTypeService: OrderSubServicesTypeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInOrderSubServicesTypes();
    }

    load(id) {
        this.orderSubServicesTypeService.find(id)
            .subscribe((orderSubServicesTypeResponse: HttpResponse<OrderSubServicesType>) => {
                this.orderSubServicesType = orderSubServicesTypeResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInOrderSubServicesTypes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'orderSubServicesTypeListModification',
            (response) => this.load(this.orderSubServicesType.id)
        );
    }
}
