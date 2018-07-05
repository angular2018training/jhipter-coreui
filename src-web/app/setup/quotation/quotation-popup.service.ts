import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { Quotation } from '../../shared/model/quotation.model';
import { QuotationService } from '../../shared/service/quotation.service';

@Injectable()
export class QuotationPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private quotationService: QuotationService

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
                this.quotationService.find(id)
                    .subscribe((quotationResponse: HttpResponse<Quotation>) => {
                        const quotation: Quotation = quotationResponse.body;
                        quotation.createDate = this.datePipe
                            .transform(quotation.createDate, 'yyyy-MM-ddTHH:mm:ss');
                        if (quotation.activeFrom) {
                            quotation.activeFrom = {
                                year: quotation.activeFrom.getFullYear(),
                                month: quotation.activeFrom.getMonth() + 1,
                                day: quotation.activeFrom.getDate()
                            };
                        }
                        this.ngbModalRef = this.quotationModalRef(component, quotation);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.quotationModalRef(component, new Quotation());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    quotationModalRef(component: Component, quotation: Quotation): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.quotation = quotation;
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
