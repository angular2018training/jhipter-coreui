import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { OrderSubServices } from '../../shared/model/order-sub-services.model';
import { OrderSubServicesService } from '../../shared/service/order-sub-services.service';

@Injectable()
export class OrderSubServicesPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private orderSubServicesService: OrderSubServicesService

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
                this.orderSubServicesService.find(id)
                    .subscribe((orderSubServicesResponse: HttpResponse<OrderSubServices>) => {
                        const orderSubServices: OrderSubServices = orderSubServicesResponse.body;
                        this.ngbModalRef = this.orderSubServicesModalRef(component, orderSubServices);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.orderSubServicesModalRef(component, new OrderSubServices());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    orderSubServicesModalRef(component: Component, orderSubServices: OrderSubServices): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.orderSubServices = orderSubServices;
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
