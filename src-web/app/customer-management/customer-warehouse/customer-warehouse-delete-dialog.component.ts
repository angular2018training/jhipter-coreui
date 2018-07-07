import { Component, OnInit, OnDestroy } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { CustomerWarehouse } from '../../shared/model/customer-warehouse.model';
import { CustomerWarehouseService } from '../../shared/service/customer-warehouse.service';

@Component({
    selector: 'jhi-customer-warehouse-delete-dialog',
    templateUrl: './customer-warehouse-delete-dialog.component.html'
})
export class CustomerWarehouseDeleteDialogComponent {
    customerWarehouse: CustomerWarehouse;
    constructor(
        private customerWarehouseService: CustomerWarehouseService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.customerWarehouseService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'customerWarehouseListModification',
                content: 'Deleted an customerWarehouse'
            });
            this.activeModal.dismiss(true);
        });
    }
}
