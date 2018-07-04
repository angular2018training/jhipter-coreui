import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { UserPostOffice } from '../../shared/model/user-post-office.model';
import { UserPostOfficeService } from '../../shared/service/user-post-office.service';
@Injectable()
export class UserPostOfficeDetailPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private userPostOfficeService: UserPostOfficeService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any , userExtraInfoParentId?: number | any  ): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.userPostOfficeService.find(id)
                    .subscribe((userPostOfficeResponse: HttpResponse<UserPostOffice>) => {
                        const userPostOffice: UserPostOffice = userPostOfficeResponse.body;
                        this.ngbModalRef = this.userPostOfficeModalRef(component, userPostOffice);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    let entity = new UserPostOffice();
                    entity.userExtraInfoParentId =  userExtraInfoParentId;
                    this.ngbModalRef = this.userPostOfficeModalRef(component, entity);
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    userPostOfficeModalRef(component: Component, userPostOffice: UserPostOffice): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.userPostOffice = userPostOffice;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: false });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: false });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
