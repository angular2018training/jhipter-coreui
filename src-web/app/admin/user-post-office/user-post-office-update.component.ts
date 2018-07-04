
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { UserPostOfficeService } from '../../shared/service/user-post-office.service';

import { UserPostOffice } from '../../shared/model/user-post-office.model';
            import { Company } from '../../shared/model/company.model';
            import { CompanyService} from '../../shared/service/company.service';
            import { PostOffice } from '../../shared/model/post-office.model';
            import { PostOfficeService} from '../../shared/service/post-office.service';
            import { UserGroup } from '../../shared/model/user-group.model';
            import { UserGroupService} from '../../shared/service/user-group.service';
            import { UserExtraInfo } from '../../shared/model/user-extra-info.model';
            import { UserExtraInfoService} from '../../shared/service/user-extra-info.service';

@Component({
    selector: 'jhi-user-post-office-update',
    templateUrl: './user-post-office-update.component.html'
})
export class UserPostOfficeUpdateComponent implements OnInit {

    private _userPostOffice: UserPostOffice;
    isSaving: boolean;
    private currentAccount : any;

    postoffices: PostOffice[];

    usergroups: UserGroup[];

    userextrainfos: UserExtraInfo[];

    constructor(
        private alertService: AlertService,
        private userPostOfficeService: UserPostOfficeService,
        private principal : Principal,
        private companyService: CompanyService,
        private postOfficeService: PostOfficeService,
        private userGroupService: UserGroupService,
        private userExtraInfoService: UserExtraInfoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({userPostOffice}) => {
            this.userPostOffice = userPostOffice;
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.postOfficeService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<PostOffice[]>) => { this.postoffices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            this.userGroupService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<UserGroup[]>) => { this.usergroups = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            this.userExtraInfoService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<UserExtraInfo[]>) => { this.userextrainfos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        });

    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.userPostOffice.id !== undefined) {
            this.subscribeToSaveResponse(
                this.userPostOfficeService.update(this.userPostOffice));
        } else {
            this.userPostOffice.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.userPostOfficeService.create(this.userPostOffice));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<UserPostOffice>>) {
        result.subscribe((res: HttpResponse<UserPostOffice>) =>
            this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.alertService.error(errorMessage, null, null);
    }

    trackCompanyById(index: number, item: Company) {
        return item.id;
    }

    trackPostOfficeById(index: number, item: PostOffice) {
        return item.id;
    }

    trackUserGroupById(index: number, item: UserGroup) {
        return item.id;
    }

    trackUserExtraInfoById(index: number, item: UserExtraInfo) {
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
    get userPostOffice() {
        return this._userPostOffice;
    }

    set userPostOffice(userPostOffice: UserPostOffice) {
        this._userPostOffice = userPostOffice;
    }
}
