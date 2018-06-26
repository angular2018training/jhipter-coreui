import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CustomerPostOffice } from './customer-post-office.model';
import { CustomerPostOfficePopupService } from './customer-post-office-popup.service';
import { CustomerPostOfficeService } from './customer-post-office.service';

@Component({
    selector: 'jhi-customer-post-office-delete-dialog',
    templateUrl: './customer-post-office-delete-dialog.component.html'
})
export class CustomerPostOfficeDeleteDialogComponent {

    customerPostOffice: CustomerPostOffice;

    constructor(
        private customerPostOfficeService: CustomerPostOfficeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.customerPostOfficeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'customerPostOfficeListModification',
                content: 'Deleted an customerPostOffice'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-customer-post-office-delete-popup',
    template: ''
})
export class CustomerPostOfficeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerPostOfficePopupService: CustomerPostOfficePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.customerPostOfficePopupService
                .open(CustomerPostOfficeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
