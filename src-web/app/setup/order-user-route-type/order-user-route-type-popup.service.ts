import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { OrderUserRouteType } from './order-user-route-type.model';
import { OrderUserRouteTypeService } from './order-user-route-type.service';

@Injectable()
export class OrderUserRouteTypePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private orderUserRouteTypeService: OrderUserRouteTypeService

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
                this.orderUserRouteTypeService.find(id)
                    .subscribe((orderUserRouteTypeResponse: HttpResponse<OrderUserRouteType>) => {
                        const orderUserRouteType: OrderUserRouteType = orderUserRouteTypeResponse.body;
                        this.ngbModalRef = this.orderUserRouteTypeModalRef(component, orderUserRouteType);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.orderUserRouteTypeModalRef(component, new OrderUserRouteType());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    orderUserRouteTypeModalRef(component: Component, orderUserRouteType: OrderUserRouteType): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.orderUserRouteType = orderUserRouteType;
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
