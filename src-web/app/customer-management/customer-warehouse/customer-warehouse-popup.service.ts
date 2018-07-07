import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { CustomerWarehouseService } from '../../shared/service/customer-warehouse.service';
import { CustomerWarehouse } from '../../shared/model/customer-warehouse.model';

@Injectable()
export class CustomerWarehousePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private customerWarehouseService: CustomerWarehouseService
    ) { this.ngbModalRef = null; }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }
            if (id) {
                this.customerWarehouseService.find(id)
                    .subscribe((customerWarehouseResponse: HttpResponse<CustomerWarehouse>) => {
                        const customerWarehouse: CustomerWarehouse = customerWarehouseResponse.body;
                        this.ngbModalRef = this.customerWarehouseModalRef(component, customerWarehouse);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.customerWarehouseModalRef(component, new CustomerWarehouse());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    customerWarehouseModalRef(component: Component, customerWarehouse: CustomerWarehouse): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.customerWarehouse = customerWarehouse;
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
