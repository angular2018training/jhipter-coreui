import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { QuotationCod } from '../../shared/model/quotation-cod.model';
import { QuotationCodService } from '../../shared/service/quotation-cod.service';
@Injectable()
export class QuotationCodDetailPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private quotationCodService: QuotationCodService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any , quotationParentId?: number | any  ): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.quotationCodService.find(id)
                    .subscribe((quotationCodResponse: HttpResponse<QuotationCod>) => {
                        const quotationCod: QuotationCod = quotationCodResponse.body;
                        this.ngbModalRef = this.quotationCodModalRef(component, quotationCod);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    let entity = new QuotationCod();
                    entity.quotationParentId =  quotationParentId;
                    this.ngbModalRef = this.quotationCodModalRef(component, entity);
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    quotationCodModalRef(component: Component, quotationCod: QuotationCod): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.quotationCod = quotationCod;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: false });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: false });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
