import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { OrderServices } from '../../shared/model/order-services.model';
import { OrderServicesService } from '../../shared/service/order-services.service';

@Component({
    selector: 'jhi-order-services-detail',
    templateUrl: './order-services-detail.component.html'
})
export class OrderServicesDetailComponent implements OnInit, OnDestroy {

    orderServices: OrderServices;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private orderServicesService: OrderServicesService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInOrderServices();
    }

    load(id) {
        this.orderServicesService.find(id)
            .subscribe((orderServicesResponse: HttpResponse<OrderServices>) => {
                this.orderServices = orderServicesResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInOrderServices() {
        this.eventSubscriber = this.eventManager.subscribe(
            'orderServicesListModification',
            (response) => this.load(this.orderServices.id)
        );
    }
}
