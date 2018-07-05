import { Component, OnInit, OnDestroy } from '@angular/core';
import { JhiEventManager } from 'ng-jhipster';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';

@Component({
    selector: 'jhi-customer-management-detail',
    templateUrl: './customer-management-detail.html'
})
export class CustomerManagementDetailComponent implements OnInit, OnDestroy {

    paymentId = 0;
    legalId = 0;
    warehouseId = 0;
    private routeSubcription: Subscription;
    constructor(
        private route: ActivatedRoute,
        private eventManager: JhiEventManager
    ) { }

    ngOnInit(): void {
        this.routeSubcription = this.route.data.subscribe(({ customer }) => {
            if (customer.paymentId) {
                this.paymentId = customer.paymentId;
            }
            if (customer.legalId) {
                this.legalId = customer.legalId;
            }
            if (customer.warehouseId) {
                this.warehouseId = this.warehouseId;
            }
        });
        this.eventManager.subscribe(
            'update-current-customer',
            (response) => {
                if (response.customer.paymentId) {
                    this.paymentId = response.customer.paymentId;
                }
                if (response.customer.legalId) {
                    this.legalId = response.customer.legalId;
                }
                if (response.customer.warehouseId) {
                    this.warehouseId = response.customer.warehouseId;
                }
            }
        );
    }

    ngOnDestroy(): void {
        this.routeSubcription.unsubscribe();
    }
}
