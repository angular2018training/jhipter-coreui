import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { QuotationReturn } from '../../shared/model/quotation-return.model';
import { QuotationReturnService } from '../../shared/service/quotation-return.service';
@Injectable()
export class QuotationReturnDetailPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private quotationReturnService: QuotationReturnService

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
                this.quotationReturnService.find(id)
                    .subscribe((quotationReturnResponse: HttpResponse<QuotationReturn>) => {
                        const quotationReturn: QuotationReturn = quotationReturnResponse.body;
                        this.ngbModalRef = this.quotationReturnModalRef(component, quotationReturn);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    let entity = new QuotationReturn();
                    entity.quotationParentId =  quotationParentId;
                    this.ngbModalRef = this.quotationReturnModalRef(component, entity);
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    quotationReturnModalRef(component: Component, quotationReturn: QuotationReturn): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.quotationReturn = quotationReturn;
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
