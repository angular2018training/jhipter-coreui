import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { OrderSubServices } from './order-sub-services.model';
import { OrderSubServicesPopupService } from './order-sub-services-popup.service';
import { OrderSubServicesService } from './order-sub-services.service';

@Component({
    selector: 'jhi-order-sub-services-delete-dialog',
    templateUrl: './order-sub-services-delete-dialog.component.html'
})
export class OrderSubServicesDeleteDialogComponent {

    orderSubServices: OrderSubServices;

    constructor(
        private orderSubServicesService: OrderSubServicesService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.orderSubServicesService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'orderSubServicesListModification',
                content: 'Deleted an orderSubServices'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-order-sub-services-delete-popup',
    template: ''
})
export class OrderSubServicesDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private orderSubServicesPopupService: OrderSubServicesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.orderSubServicesPopupService
                .open(OrderSubServicesDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
