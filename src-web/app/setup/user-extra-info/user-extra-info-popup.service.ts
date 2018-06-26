import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { UserExtraInfo } from './user-extra-info.model';
import { UserExtraInfoService } from './user-extra-info.service';

@Injectable()
export class UserExtraInfoPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private datePipe: DatePipe,
        private modalService: NgbModal,
        private router: Router,
        private userExtraInfoService: UserExtraInfoService

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
                this.userExtraInfoService.find(id)
                    .subscribe((userExtraInfoResponse: HttpResponse<UserExtraInfo>) => {
                        const userExtraInfo: UserExtraInfo = userExtraInfoResponse.body;
                        userExtraInfo.validDate = this.datePipe
                            .transform(userExtraInfo.validDate, 'yyyy-MM-ddTHH:mm:ss');
                        userExtraInfo.lastLoginDate = this.datePipe
                            .transform(userExtraInfo.lastLoginDate, 'yyyy-MM-ddTHH:mm:ss');
                        userExtraInfo.contractExpirationDate = this.datePipe
                            .transform(userExtraInfo.contractExpirationDate, 'yyyy-MM-ddTHH:mm:ss');
                        this.ngbModalRef = this.userExtraInfoModalRef(component, userExtraInfo);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.userExtraInfoModalRef(component, new UserExtraInfo());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    userExtraInfoModalRef(component: Component, userExtraInfo: UserExtraInfo): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.userExtraInfo = userExtraInfo;
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
