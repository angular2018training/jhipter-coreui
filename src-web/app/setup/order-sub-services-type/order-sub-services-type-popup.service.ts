import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { OrderSubServicesType } from '../../shared/model/order-sub-services-type.model';
import { OrderSubServicesTypeService } from '../../shared/service/order-sub-services-type.service';

@Injectable()
export class OrderSubServicesTypePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private orderSubServicesTypeService: OrderSubServicesTypeService

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
                this.orderSubServicesTypeService.find(id)
                    .subscribe((orderSubServicesTypeResponse: HttpResponse<OrderSubServicesType>) => {
                        const orderSubServicesType: OrderSubServicesType = orderSubServicesTypeResponse.body;
                        this.ngbModalRef = this.orderSubServicesTypeModalRef(component, orderSubServicesType);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.orderSubServicesTypeModalRef(component, new OrderSubServicesType());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    orderSubServicesTypeModalRef(component: Component, orderSubServicesType: OrderSubServicesType): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.orderSubServicesType = orderSubServicesType;
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
