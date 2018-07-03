import { Component } from '@angular/core';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CustomerPostOffice } from './customer-post-office.model';
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
