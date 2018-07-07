import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { QuotationSubServices } from '../../shared/model/quotation-sub-services.model';
import { QuotationSubServicesService } from '../../shared/service/quotation-sub-services.service';
@Injectable()
export class QuotationSubServicesDetailPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private quotationSubServicesService: QuotationSubServicesService

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
                this.quotationSubServicesService.find(id)
                    .subscribe((quotationSubServicesResponse: HttpResponse<QuotationSubServices>) => {
                        const quotationSubServices: QuotationSubServices = quotationSubServicesResponse.body;
                        this.ngbModalRef = this.quotationSubServicesModalRef(component, quotationSubServices);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    let entity = new QuotationSubServices();
                    entity.quotationParentId =  quotationParentId;
                    this.ngbModalRef = this.quotationSubServicesModalRef(component, entity);
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    quotationSubServicesModalRef(component: Component, quotationSubServices: QuotationSubServices): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.quotationSubServices = quotationSubServices;
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
