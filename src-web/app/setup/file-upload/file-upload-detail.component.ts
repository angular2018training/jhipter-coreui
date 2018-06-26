import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiDataUtils } from 'ng-jhipster';

import { FileUpload } from './file-upload.model';
import { FileUploadService } from './file-upload.service';

@Component({
    selector: 'jhi-file-upload-detail',
    templateUrl: './file-upload-detail.component.html'
})
export class FileUploadDetailComponent implements OnInit, OnDestroy {

    fileUpload: FileUpload;
    private subscription: Subscription;
    private eventSubscriber: Subscription;

    constructor(
        private eventManager: JhiEventManager,
        private dataUtils: JhiDataUtils,
        private fileUploadService: FileUploadService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.subscription = this.route.params.subscribe((params) => {
            this.load(params['id']);
        });
        this.registerChangeInFileUploads();
    }

    load(id) {
        this.fileUploadService.find(id)
            .subscribe((fileUploadResponse: HttpResponse<FileUpload>) => {
                this.fileUpload = fileUploadResponse.body;
            });
    }
    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }

    ngOnDestroy() {
        this.subscription.unsubscribe();
        this.eventManager.destroy(this.eventSubscriber);
    }

    registerChangeInFileUploads() {
        this.eventSubscriber = this.eventManager.subscribe(
            'fileUploadListModification',
            (response) => this.load(this.fileUpload.id)
        );
    }
}
