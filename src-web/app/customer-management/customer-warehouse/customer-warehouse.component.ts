import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgForm } from '@angular/forms';
import { AlertService } from '../../shared/alert/alert-service';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager } from 'ng-jhipster';
import { CustomerWarehousePopupService } from './customer-warehouse-popup.service';
import { CustomerWarehouseDeleteDialogComponent } from './customer-warehouse-delete-dialog.component';
import { Principal } from '../../shared';
import { CustomerWarehouse } from '../../shared/model/customer-warehouse.model';
import { CustomerWarehouseService } from '../../shared/service/customer-warehouse.service';
import { WarehousePopupService } from './warehouse/warehouse-popup.service';
import { WarehouseUpdateComponent } from './warehouse/warehouse-update.component';

@Component({
    selector: 'jhi-customer-warehouse',
    templateUrl: './customer-warehouse.component.html',
    providers: [CustomerWarehousePopupService, WarehousePopupService]
})
export class CustomerWarehouseComponent implements OnInit, OnDestroy {
    customerId: number;
    currentAccount: any;
    customerWarehouses: CustomerWarehouse[];
    eventSubscriber: Subscription;

    @ViewChild(NgForm) searchForm: NgForm;
    constructor(
        private customerWarehouseService: CustomerWarehouseService,
        private principal: Principal,
        private activatedRoute: ActivatedRoute,
        private alertService: AlertService,
        private eventManager: JhiEventManager,
        private customerWarehousePopupService: CustomerWarehousePopupService,
        private warehousePopupService: WarehousePopupService
    ) { }

    ngOnInit() {
        this.activatedRoute.parent.params.subscribe((param) => {
            this.customerId = param.id;
            this.loadWarehouses();
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });
        this.registerChangeInCustomerWarehouses();
    }

    private loadWarehouses() {
        this.customerWarehouseService.query({
            'customerParentId.equals': this.customerId,
            size: 10000
        }).subscribe((res) => this.customerWarehouses = res.body);
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: CustomerWarehouse) {
        return item.id;
    }

    registerChangeInCustomerWarehouses() {
        this.eventSubscriber = this.eventManager.subscribe('customerWarehouseListModification', (response) => this.loadWarehouses());
    }

    showWareHouseDetail(customerWarehouse?: CustomerWarehouse) {
        if (!customerWarehouse) {
            customerWarehouse = new CustomerWarehouse();
            customerWarehouse.customerParentId = this.customerId;
        }
        this.warehousePopupService.open(WarehouseUpdateComponent as Component, customerWarehouse);
    }

    private onSuccess(data, headers) {
        this.customerWarehouses = data;
    }

    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

    public deleteItem(id: number) {
        this.customerWarehousePopupService
            .open(CustomerWarehouseDeleteDialogComponent as Component, id);
    }
}
