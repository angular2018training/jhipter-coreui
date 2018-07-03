import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { OrderServices } from '../../shared/model/order-services.model';
import { OrderServicesPopupService } from './order-services-popup.service';
import { OrderServicesService } from '../../shared/service/order-services.service';

@Component({
    selector: 'jhi-order-services-delete-dialog',
    templateUrl: './order-services-delete-dialog.component.html'
})
export class OrderServicesDeleteDialogComponent {

    orderServices: OrderServices;

    constructor(
        private orderServicesService: OrderServicesService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.orderServicesService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'orderServicesListModification',
                content: 'Deleted an orderServices'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-order-services-delete-popup',
    template: ''
})
export class OrderServicesDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private orderServicesPopupService: OrderServicesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.orderServicesPopupService
                .open(OrderServicesDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
