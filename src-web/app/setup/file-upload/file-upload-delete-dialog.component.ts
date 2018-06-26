import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { FileUpload } from './file-upload.model';
import { FileUploadPopupService } from './file-upload-popup.service';
import { FileUploadService } from './file-upload.service';

@Component({
    selector: 'jhi-file-upload-delete-dialog',
    templateUrl: './file-upload-delete-dialog.component.html'
})
export class FileUploadDeleteDialogComponent {

    fileUpload: FileUpload;

    constructor(
        private fileUploadService: FileUploadService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.fileUploadService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'fileUploadListModification',
                content: 'Deleted an fileUpload'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-file-upload-delete-popup',
    template: ''
})
export class FileUploadDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fileUploadPopupService: FileUploadPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.fileUploadPopupService
                .open(FileUploadDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
