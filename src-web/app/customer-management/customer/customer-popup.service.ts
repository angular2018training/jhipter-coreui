import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { Customer } from './customer.model';
import { CustomerService } from './customer.service';

@Injectable()
export class CustomerPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private customerService: CustomerService

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
                this.customerService.find(id)
                    .subscribe((customerResponse: HttpResponse<Customer>) => {
                        const customer: Customer = customerResponse.body;
                        customer.createDate = this.datePipe
                            .transform(customer.createDate, 'yyyy-MM-ddTHH:mm:ss');
                        customer.lastLoginDate = this.datePipe
                            .transform(customer.lastLoginDate, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.customerModalRef(component, customer);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.customerModalRef(component, new Customer());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    customerModalRef(component: Component, customer: Customer): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.customer = customer;
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
