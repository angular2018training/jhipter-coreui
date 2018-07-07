import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { OrderSubServicesType } from '../../shared/model/order-sub-services-type.model';
import { OrderSubServicesTypePopupService } from './order-sub-services-type-popup.service';
import { OrderSubServicesTypeService } from '../../shared/service/order-sub-services-type.service';

@Component({
    selector: 'jhi-order-sub-services-type-delete-dialog',
    templateUrl: './order-sub-services-type-delete-dialog.component.html'
})
export class OrderSubServicesTypeDeleteDialogComponent {

    orderSubServicesType: OrderSubServicesType;

    constructor(
        private orderSubServicesTypeService: OrderSubServicesTypeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.orderSubServicesTypeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'orderSubServicesTypeListModification',
                content: 'Deleted an orderSubServicesType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-order-sub-services-type-delete-popup',
    template: ''
})
export class OrderSubServicesTypeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private orderSubServicesTypePopupService: OrderSubServicesTypePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.orderSubServicesTypePopupService
                .open(OrderSubServicesTypeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
