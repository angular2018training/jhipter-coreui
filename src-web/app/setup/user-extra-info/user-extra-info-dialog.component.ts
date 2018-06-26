import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { UserExtraInfo } from './user-extra-info.model';
import { UserExtraInfoPopupService } from './user-extra-info-popup.service';
import { UserExtraInfoService } from './user-extra-info.service';
import { Role, RoleService } from '../role';
import { UserGroup, UserGroupService } from '../user-group';

@Component({
    selector: 'jhi-user-extra-info-dialog',
    templateUrl: './user-extra-info-dialog.component.html'
})
export class UserExtraInfoDialogComponent implements OnInit {

    userExtraInfo: UserExtraInfo;
    isSaving: boolean;

    roles: Role[];

    usergroups: UserGroup[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private userExtraInfoService: UserExtraInfoService,
        private roleService: RoleService,
        private userGroupService: UserGroupService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.roleService.query()
            .subscribe((res: HttpResponse<Role[]>) => { this.roles = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.userGroupService.query()
            .subscribe((res: HttpResponse<UserGroup[]>) => { this.usergroups = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.userExtraInfo.id !== undefined) {
            this.subscribeToSaveResponse(
                this.userExtraInfoService.update(this.userExtraInfo));
        } else {
            this.subscribeToSaveResponse(
                this.userExtraInfoService.create(this.userExtraInfo));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<UserExtraInfo>>) {
        result.subscribe((res: HttpResponse<UserExtraInfo>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: UserExtraInfo) {
        this.eventManager.broadcast({ name: 'userExtraInfoListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackRoleById(index: number, item: Role) {
        return item.id;
    }

    trackUserGroupById(index: number, item: UserGroup) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-user-extra-info-popup',
    template: ''
})
export class UserExtraInfoPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userExtraInfoPopupService: UserExtraInfoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.userExtraInfoPopupService
                    .open(UserExtraInfoDialogComponent as Component, params['id']);
            } else {
                this.userExtraInfoPopupService
                    .open(UserExtraInfoDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
