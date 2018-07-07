import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { CustomerPostOfficeService } from '../../shared/service/customer-post-office.service';
import { CustomerPostOffice } from '../../shared/model/customer-post-office.model';

@Injectable()
export class CustomerPostOfficePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private customerPostOfficeService: CustomerPostOfficeService
    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any, customerId?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }
            if (id) {
                this.customerPostOfficeService.find(id)
                    .subscribe((customerPostOfficeResponse: HttpResponse<CustomerPostOffice>) => {
                        const customerPostOffice: CustomerPostOffice = customerPostOfficeResponse.body;
                        customerPostOffice.customerParentId = customerId;
                        this.ngbModalRef = this.customerPostOfficeModalRef(component, customerPostOffice);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    const customerPostOffice = new CustomerPostOffice();
                    customerPostOffice.customerParentId = customerId;
                    customerPostOffice.createDate = new Date();
                    this.ngbModalRef = this.customerPostOfficeModalRef(component, customerPostOffice);
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    customerPostOfficeModalRef(component: Component, customerPostOffice: CustomerPostOffice): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.customerPostOffice = customerPostOffice;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
