import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { OrderSubServices } from '../../shared/model/order-sub-services.model';
import { OrderSubServicesService } from '../../shared/service/order-sub-services.service';

@Component({
    selector: 'jhi-order-sub-services-detail',
    templateUrl: './order-sub-services-detail.component.html'
})
export class OrderSubServicesDetailComponent implements OnInit, OnDestroy {

    orderSubServices: OrderSubServices;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private orderSubServicesService: OrderSubServicesService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInOrderSubServices();
    }

    load(id) {
        this.orderSubServicesService.find(id)
            .subscribe((orderSubServicesResponse: HttpResponse<OrderSubServices>) => {
                this.orderSubServices = orderSubServicesResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInOrderSubServices() {
        this.eventSubscriber = this.eventManager.subscribe(
            'orderSubServicesListModification',
            (response) => this.load(this.orderSubServices.id)
        );
    }
}
