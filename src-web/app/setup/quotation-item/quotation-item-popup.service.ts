import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { QuotationItem } from '../../shared/model/quotation-item.model';
import { QuotationItemService } from '../../shared/service/quotation-item.service';

@Injectable()
export class QuotationItemPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private quotationItemService: QuotationItemService

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
                this.quotationItemService.find(id)
                    .subscribe((quotationItemResponse: HttpResponse<QuotationItem>) => {
                        const quotationItem: QuotationItem = quotationItemResponse.body;
                        quotationItem.createDate = this.datePipe
                            .transform(quotationItem.createDate, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.quotationItemModalRef(component, quotationItem);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.quotationItemModalRef(component, new QuotationItem());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    quotationItemModalRef(component: Component, quotationItem: QuotationItem): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.quotationItem = quotationItem;
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
