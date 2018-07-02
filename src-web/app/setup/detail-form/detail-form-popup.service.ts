import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { DetailForm } from './detail-form.model';
import { DetailFormService } from './detail-form.service';

@Injectable()
export class DetailFormPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private detailFormService: DetailFormService

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
                this.detailFormService.find(id)
                    .subscribe((detailFormResponse: HttpResponse<DetailForm>) => {
                        const detailForm: DetailForm = detailFormResponse.body;
                        detailForm.createDate = this.datePipe
                            .transform(detailForm.createDate, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.detailFormModalRef(component, detailForm);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.detailFormModalRef(component, new DetailForm());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    detailFormModalRef(component: Component, detailForm: DetailForm): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.detailForm = detailForm;
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
