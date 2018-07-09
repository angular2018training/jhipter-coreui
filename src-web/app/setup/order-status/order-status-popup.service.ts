import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { OrderStatus } from './order-status.model';
import { OrderStatusService } from './order-status.service';

@Injectable()
export class OrderStatusPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private orderStatusService: OrderStatusService

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
                this.orderStatusService.find(id)
                    .subscribe((orderStatusResponse: HttpResponse<OrderStatus>) => {
                        const orderStatus: OrderStatus = orderStatusResponse.body;
                        this.ngbModalRef = this.orderStatusModalRef(component, orderStatus);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.orderStatusModalRef(component, new OrderStatus());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    orderStatusModalRef(component: Component, orderStatus: OrderStatus): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.orderStatus = orderStatus;
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
