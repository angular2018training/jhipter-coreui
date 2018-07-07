import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { QuotationGiveBack } from '../../shared/model/quotation-give-back.model';
import { QuotationGiveBackService } from '../../shared/service/quotation-give-back.service';

@Injectable()
export class QuotationGiveBackPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private quotationGiveBackService: QuotationGiveBackService

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
                this.quotationGiveBackService.find(id)
                    .subscribe((quotationGiveBackResponse: HttpResponse<QuotationGiveBack>) => {
                        const quotationGiveBack: QuotationGiveBack = quotationGiveBackResponse.body;
                        this.ngbModalRef = this.quotationGiveBackModalRef(component, quotationGiveBack);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.quotationGiveBackModalRef(component, new QuotationGiveBack());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    quotationGiveBackModalRef(component: Component, quotationGiveBack: QuotationGiveBack): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.quotationGiveBack = quotationGiveBack;
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
