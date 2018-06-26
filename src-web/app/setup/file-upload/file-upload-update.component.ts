import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from '../../shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { FileUploadService } from './file-upload.service';

import { FileUpload } from './file-upload.model';
            import { Company, CompanyService } from '../company';

@Component({
    selector: 'jhi-file-upload-update',
    templateUrl: './file-upload-update.component.html'
})
export class FileUploadUpdateComponent implements OnInit {

    private _fileUpload: FileUpload;
    isSaving: boolean;

    companies: Company[];
    uploadTime: string;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private fileUploadService: FileUploadService,
        private companyService: CompanyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({fileUpload}) => {
            this.fileUpload = fileUpload;
        });
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
                this.fileUpload.uploadTime = moment(this.uploadTime, DATE_TIME_FORMAT);
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
            this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCompanyById(index: number, item: Company) {
        return item.id;
    }
    get fileUpload() {
        return this._fileUpload;
    }

    set fileUpload(fileUpload: FileUpload) {
        this._fileUpload = fileUpload;
        this.uploadTime = moment(fileUpload.uploadTime).format(DATE_TIME_FORMAT);
    }
}
