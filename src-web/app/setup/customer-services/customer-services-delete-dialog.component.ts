import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CustomerServices } from './customer-services.model';
import { CustomerServicesPopupService } from './customer-services-popup.service';
import { CustomerServicesService } from './customer-services.service';

@Component({
    selector: 'jhi-customer-services-delete-dialog',
    templateUrl: './customer-services-delete-dialog.component.html'
})
export class CustomerServicesDeleteDialogComponent {

    customerServices: CustomerServices;

    constructor(
        private customerServicesService: CustomerServicesService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.customerServicesService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'customerServicesListModification',
                content: 'Deleted an customerServices'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-customer-services-delete-popup',
    template: ''
})
export class CustomerServicesDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerServicesPopupService: CustomerServicesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.customerServicesPopupService
                .open(CustomerServicesDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
