import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { CustomerPostOffice } from './customer-post-office.model';
import { CustomerPostOfficeService } from './customer-post-office.service';

@Injectable()
export class CustomerPostOfficePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private customerPostOfficeService: CustomerPostOfficeService

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
                this.customerPostOfficeService.find(id)
                    .subscribe((customerPostOfficeResponse: HttpResponse<CustomerPostOffice>) => {
                        const customerPostOffice: CustomerPostOffice = customerPostOfficeResponse.body;
                        customerPostOffice.createDate = this.datePipe
                            .transform(customerPostOffice.createDate, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.customerPostOfficeModalRef(component, customerPostOffice);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.customerPostOfficeModalRef(component, new CustomerPostOffice());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    customerPostOfficeModalRef(component: Component, customerPostOffice: CustomerPostOffice): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.customerPostOffice = customerPostOffice;
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
