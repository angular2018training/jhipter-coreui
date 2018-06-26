import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Warehouse } from './warehouse.model';
import { WarehousePopupService } from './warehouse-popup.service';
import { WarehouseService } from './warehouse.service';

@Component({
    selector: 'jhi-warehouse-delete-dialog',
    templateUrl: './warehouse-delete-dialog.component.html'
})
export class WarehouseDeleteDialogComponent {

    warehouse: Warehouse;

    constructor(
        private warehouseService: WarehouseService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.warehouseService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'warehouseListModification',
                content: 'Deleted an warehouse'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-warehouse-delete-popup',
    template: ''
})
export class WarehouseDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private warehousePopupService: WarehousePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.warehousePopupService
                .open(WarehouseDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
