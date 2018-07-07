import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { Warehouse } from '../../../shared/model/warehouse.model';
import { WarehouseService } from '../../../shared/service/warehouse.service';
import { CustomerWarehouse } from '../../../shared/model/customer-warehouse.model';

@Injectable()
export class WarehousePopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private warehouseService: WarehouseService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, customerWarehouse: CustomerWarehouse): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (customerWarehouse && customerWarehouse.warehouseId) {
                this.warehouseService.find(customerWarehouse.warehouseId)
                    .subscribe((warehouseResponse: HttpResponse<Warehouse>) => {
                        const warehouse: Warehouse = warehouseResponse.body;
                        warehouse.createDate = new Date(warehouse.createDate);
                        // this.datePipe
                        //     .transform(warehouse.createDate, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.warehouseModalRef(component, warehouse, customerWarehouse);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    const warehouse = new Warehouse();
                    warehouse.companyId = customerWarehouse.companyId;
                    warehouse.createDate = new Date();
                    this.ngbModalRef = this.warehouseModalRef(component, warehouse, customerWarehouse);
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    warehouseModalRef(component: Component, warehouse: Warehouse, customerWarehouse: CustomerWarehouse): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static' });
        modalRef.componentInstance.warehouse = warehouse;
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
