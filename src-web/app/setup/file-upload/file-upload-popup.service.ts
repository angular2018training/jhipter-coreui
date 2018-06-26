import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { FileUpload } from './file-upload.model';
import { FileUploadService } from './file-upload.service';

@Injectable()
export class FileUploadPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private fileUploadService: FileUploadService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.fileUploadService.find(id)
                    .subscribe((fileUploadResponse: HttpResponse<FileUpload>) => {
                        const fileUpload: FileUpload = fileUploadResponse.body;
                        fileUpload.uploadTime = this.datePipe
                            .transform(fileUpload.uploadTime, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.fileUploadModalRef(component, fileUpload);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.fileUploadModalRef(component, new FileUpload());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    fileUploadModalRef(component: Component, fileUpload: FileUpload): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.fileUpload = fileUpload;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
