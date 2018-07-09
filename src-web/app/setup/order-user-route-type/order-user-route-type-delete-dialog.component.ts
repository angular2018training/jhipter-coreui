import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { OrderUserRouteType } from './order-user-route-type.model';
import { OrderUserRouteTypePopupService } from './order-user-route-type-popup.service';
import { OrderUserRouteTypeService } from './order-user-route-type.service';

@Component({
    selector: 'jhi-order-user-route-type-delete-dialog',
    templateUrl: './order-user-route-type-delete-dialog.component.html'
})
export class OrderUserRouteTypeDeleteDialogComponent {

    orderUserRouteType: OrderUserRouteType;

    constructor(
        private orderUserRouteTypeService: OrderUserRouteTypeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.orderUserRouteTypeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'orderUserRouteTypeListModification',
                content: 'Deleted an orderUserRouteType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-order-user-route-type-delete-popup',
    template: ''
})
export class OrderUserRouteTypeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private orderUserRouteTypePopupService: OrderUserRouteTypePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.orderUserRouteTypePopupService
                .open(OrderUserRouteTypeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
