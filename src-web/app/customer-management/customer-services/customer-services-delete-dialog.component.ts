
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CustomerServices } from '../../shared/model/customer-services.model';
import { CustomerServicesService } from '../../shared/service/customer-services.service';
import { Component } from '@angular/core';

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
    ) { }

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
