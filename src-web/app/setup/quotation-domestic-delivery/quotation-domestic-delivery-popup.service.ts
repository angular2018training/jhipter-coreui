import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { QuotationDomesticDelivery } from '../../shared/model/quotation-domestic-delivery.model';
import { QuotationDomesticDeliveryService } from '../../shared/service/quotation-domestic-delivery.service';

@Injectable()
export class QuotationDomesticDeliveryPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private quotationDomesticDeliveryService: QuotationDomesticDeliveryService

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
                this.quotationDomesticDeliveryService.find(id)
                    .subscribe((quotationDomesticDeliveryResponse: HttpResponse<QuotationDomesticDelivery>) => {
                        const quotationDomesticDelivery: QuotationDomesticDelivery = quotationDomesticDeliveryResponse.body;
                        this.ngbModalRef = this.quotationDomesticDeliveryModalRef(component, quotationDomesticDelivery);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.quotationDomesticDeliveryModalRef(component, new QuotationDomesticDelivery());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    quotationDomesticDeliveryModalRef(component: Component, quotationDomesticDelivery: QuotationDomesticDelivery): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.quotationDomesticDelivery = quotationDomesticDelivery;
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
