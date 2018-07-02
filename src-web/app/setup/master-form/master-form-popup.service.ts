import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { MasterForm } from './master-form.model';
import { MasterFormService } from './master-form.service';

@Injectable()
export class MasterFormPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private masterFormService: MasterFormService

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
                this.masterFormService.find(id)
                    .subscribe((masterFormResponse: HttpResponse<MasterForm>) => {
                        const masterForm: MasterForm = masterFormResponse.body;
                        masterForm.receiveTime = this.datePipe
                            .transform(masterForm.receiveTime, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.masterFormModalRef(component, masterForm);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.masterFormModalRef(component, new MasterForm());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    masterFormModalRef(component: Component, masterForm: MasterForm): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.masterForm = masterForm;
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
