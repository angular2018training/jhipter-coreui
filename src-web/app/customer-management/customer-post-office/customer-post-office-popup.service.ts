import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { CustomerPostOffice } from './customer-post-office.model';
import { CustomerPostOfficeService } from './customer-post-office.service';
import { DATE_TIME_FORMAT } from '../../shared/constants/input.constants';
import * as moment from 'moment';

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
                        customerPostOffice.createDate = moment(customerPostOffice.createDate).format(DATE_TIME_FORMAT);
                        customerPostOffice.customerId = customerId;
                        this.ngbModalRef = this.customerPostOfficeModalRef(component, customerPostOffice);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    const customerPostOffice = new CustomerPostOffice();
                    customerPostOffice.customerId = customerId;
                    customerPostOffice.createDate = moment().format(DATE_TIME_FORMAT);
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
