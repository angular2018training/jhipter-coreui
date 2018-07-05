import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';
import { CustomerLegalFileUpload } from '../../shared/model/customer-legal-file-upload.model';
import { CustomerLegalFileUploadService } from '../../shared/service/customer-legal-file-upload.service';

@Component({
    selector: 'jhi-customer-legal-file-upload-delete-dialog',
    templateUrl: './customer-legal-file-upload-delete-dialog.component.html'
})
export class CustomerLegalFileUploadDeleteDialogComponent {

    customerLegalFileUpload: CustomerLegalFileUpload;

    constructor(
        private customerLegalFileUploadService: CustomerLegalFileUploadService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.customerLegalFileUploadService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'customerLegalFileUploadListModification',
                content: 'Deleted an customerLegalFileUpload'
            });
            this.activeModal.dismiss(true);
        });
    }
}