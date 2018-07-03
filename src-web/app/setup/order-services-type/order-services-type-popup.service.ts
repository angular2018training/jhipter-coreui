import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { OrderServicesType } from './order-services-type.model';
import { OrderServicesTypeService } from './order-services-type.service';

@Injectable()
export class OrderServicesTypePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private orderServicesTypeService: OrderServicesTypeService

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
                this.orderServicesTypeService.find(id)
                    .subscribe((orderServicesTypeResponse: HttpResponse<OrderServicesType>) => {
                        const orderServicesType: OrderServicesType = orderServicesTypeResponse.body;
                        this.ngbModalRef = this.orderServicesTypeModalRef(component, orderServicesType);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.orderServicesTypeModalRef(component, new OrderServicesType());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    orderServicesTypeModalRef(component: Component, orderServicesType: OrderServicesType): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.orderServicesType = orderServicesType;
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
