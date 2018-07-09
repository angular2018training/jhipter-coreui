import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { QuotationType } from './quotation-type.model';
import { QuotationTypeService } from './quotation-type.service';

@Injectable()
export class QuotationTypePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private quotationTypeService: QuotationTypeService

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
                this.quotationTypeService.find(id)
                    .subscribe((quotationTypeResponse: HttpResponse<QuotationType>) => {
                        const quotationType: QuotationType = quotationTypeResponse.body;
                        this.ngbModalRef = this.quotationTypeModalRef(component, quotationType);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.quotationTypeModalRef(component, new QuotationType());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    quotationTypeModalRef(component: Component, quotationType: QuotationType): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.quotationType = quotationType;
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
