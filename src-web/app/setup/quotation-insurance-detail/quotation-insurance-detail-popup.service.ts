import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { QuotationInsurance } from '../../shared/model/quotation-insurance.model';
import { QuotationInsuranceService } from '../../shared/service/quotation-insurance.service';
@Injectable()
export class QuotationInsuranceDetailPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private quotationInsuranceService: QuotationInsuranceService

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
                this.quotationInsuranceService.find(id)
                    .subscribe((quotationInsuranceResponse: HttpResponse<QuotationInsurance>) => {
                        const quotationInsurance: QuotationInsurance = quotationInsuranceResponse.body;
                        this.ngbModalRef = this.quotationInsuranceModalRef(component, quotationInsurance);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    let entity = new QuotationInsurance();
                    entity.quotationParentId =  quotationParentId;
                    this.ngbModalRef = this.quotationInsuranceModalRef(component, entity);
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    quotationInsuranceModalRef(component: Component, quotationInsurance: QuotationInsurance): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.quotationInsurance = quotationInsurance;
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
