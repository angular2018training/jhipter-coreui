import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { OrderServicesType } from './order-services-type.model';
import { OrderServicesTypeService } from './order-services-type.service';

@Component({
    selector: 'jhi-order-services-type-detail',
    templateUrl: './order-services-type-detail.component.html'
})
export class OrderServicesTypeDetailComponent implements OnInit, OnDestroy {

    orderServicesType: OrderServicesType;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private orderServicesTypeService: OrderServicesTypeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInOrderServicesTypes();
    }

    load(id) {
        this.orderServicesTypeService.find(id)
            .subscribe((orderServicesTypeResponse: HttpResponse<OrderServicesType>) => {
                this.orderServicesType = orderServicesTypeResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInOrderServicesTypes() {
        this.eventSubscriber = this.eventManager.subscribe(
            'orderServicesTypeListModification',
            (response) => this.load(this.orderServicesType.id)
        );
    }
}
