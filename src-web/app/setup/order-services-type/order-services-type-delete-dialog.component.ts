import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { OrderServicesType } from './order-services-type.model';
import { OrderServicesTypePopupService } from './order-services-type-popup.service';
import { OrderServicesTypeService } from './order-services-type.service';

@Component({
    selector: 'jhi-order-services-type-delete-dialog',
    templateUrl: './order-services-type-delete-dialog.component.html'
})
export class OrderServicesTypeDeleteDialogComponent {

    orderServicesType: OrderServicesType;

    constructor(
        private orderServicesTypeService: OrderServicesTypeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.orderServicesTypeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'orderServicesTypeListModification',
                content: 'Deleted an orderServicesType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-order-services-type-delete-popup',
    template: ''
})
export class OrderServicesTypeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private orderServicesTypePopupService: OrderServicesTypePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.orderServicesTypePopupService
                .open(OrderServicesTypeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
