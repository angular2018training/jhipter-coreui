import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { OrderStatus } from './order-status.model';
import { OrderStatusPopupService } from './order-status-popup.service';
import { OrderStatusService } from './order-status.service';

@Component({
    selector: 'jhi-order-status-delete-dialog',
    templateUrl: './order-status-delete-dialog.component.html'
})
export class OrderStatusDeleteDialogComponent {

    orderStatus: OrderStatus;

    constructor(
        private orderStatusService: OrderStatusService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.orderStatusService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'orderStatusListModification',
                content: 'Deleted an orderStatus'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-order-status-delete-popup',
    template: ''
})
export class OrderStatusDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private orderStatusPopupService: OrderStatusPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.orderStatusPopupService
                .open(OrderStatusDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
