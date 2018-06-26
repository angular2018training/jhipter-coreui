import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { CustomerPayment } from './customer-payment.model';
import { CustomerPaymentService } from './customer-payment.service';

@Injectable()
export class CustomerPaymentPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private customerPaymentService: CustomerPaymentService

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
                this.customerPaymentService.find(id)
                    .subscribe((customerPaymentResponse: HttpResponse<CustomerPayment>) => {
                        const customerPayment: CustomerPayment = customerPaymentResponse.body;
                        customerPayment.dateVerify = this.datePipe
                            .transform(customerPayment.dateVerify, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.customerPaymentModalRef(component, customerPayment);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.customerPaymentModalRef(component, new CustomerPayment());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    customerPaymentModalRef(component: Component, customerPayment: CustomerPayment): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.customerPayment = customerPayment;
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
