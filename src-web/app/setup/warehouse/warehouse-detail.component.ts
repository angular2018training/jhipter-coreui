import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';

import { Warehouse } from './warehouse.model';
import { WarehouseService } from './warehouse.service';

@Component({
    selector: 'jhi-warehouse-detail',
    templateUrl: './warehouse-detail.component.html'
})
export class WarehouseDetailComponent implements OnInit, OnDestroy {

    warehouse: Warehouse;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private warehouseService: WarehouseService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInWarehouses();
    }

    load(id) {
        this.warehouseService.find(id)
            .subscribe((warehouseResponse: HttpResponse<Warehouse>) => {
                this.warehouse = warehouseResponse.body;
            });
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInWarehouses() {
        this.eventSubscriber = this.eventManager.subscribe(
            'warehouseListModification',
            (response) => this.load(this.warehouse.id)
        );
    }
}
