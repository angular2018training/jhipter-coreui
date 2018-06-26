import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { FileUpload } from './file-upload.model';
import { FileUploadPopupService } from './file-upload-popup.service';
import { FileUploadService } from './file-upload.service';

@Component({
    selector: 'jhi-file-upload-dialog',
    templateUrl: './file-upload-dialog.component.html'
})
export class FileUploadDialogComponent implements OnInit {

    fileUpload: FileUpload;
    isSaving: boolean;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private fileUploadService: FileUploadService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.fileUpload.id !== undefined) {
            this.subscribeToSaveResponse(
                this.fileUploadService.update(this.fileUpload));
        } else {
            this.subscribeToSaveResponse(
                this.fileUploadService.create(this.fileUpload));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<FileUpload>>) {
        result.subscribe((res: HttpResponse<FileUpload>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: FileUpload) {
        this.eventManager.broadcast({ name: 'fileUploadListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }
}

@Component({
    selector: 'jhi-file-upload-popup',
    template: ''
})
export class FileUploadPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private fileUploadPopupService: FileUploadPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.fileUploadPopupService
                    .open(FileUploadDialogComponent as Component, params['id']);
            } else {
                this.fileUploadPopupService
                    .open(FileUploadDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
