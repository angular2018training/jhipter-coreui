import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { UserPostOffice } from '../../shared/model/user-post-office.model';
import { Company } from '../../shared/model/company.model';
import { AlertService } from '../../shared/alert/alert-service';
import { UserPostOfficeService } from '../../shared/service/user-post-office.service';
import { CompanyService } from '../../shared/service/company.service';
import { PostOfficeService } from '../../shared/service/post-office.service';
import {PostOffice} from "../../shared/model/post-office.model";
import {UserGroup} from "../../shared/model/user-group.model";
import {UserGroupService} from "../../shared/service/user-group.service";
import {Principal} from "../../shared/auth/principal.service";
import {ITEMS_QUERY_ALL} from "../../shared/constants/pagination.constants";

@Component({
    selector: 'jhi-user-post-office-detail-update',
    templateUrl: './user-post-office-detail-update.component.html'
})
export class UserPostOfficeDetailUpdateComponent implements OnInit {

    userPostOffice: UserPostOffice;
    isSaving: boolean;

    companies: Company[];

    postOffices: PostOffice[];

    userGroups: UserGroup[];
    currentAccount : any;
    constructor(
        public activeModal: NgbActiveModal,
        private alertService: AlertService,
        private userPostOfficeService: UserPostOfficeService,
        private companyService: CompanyService,
        private postOfficeService: PostOfficeService,
        private userGroupService: UserGroupService,
        private eventManager: JhiEventManager,
        private principal:Principal
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.principal.identity().then((account) => {
          this.currentAccount = account;
          this.postOfficeService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<PostOffice[]>) => { this.postOffices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
          this.userGroupService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<UserGroup[]>) => { this.userGroups = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        });


    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        this.userPostOffice.companyId= this.currentAccount.companyId;
        if (this.userPostOffice.id !== undefined) {
            this.subscribeToSaveResponse(
                this.userPostOfficeService.update(this.userPostOffice));
        } else {
            this.subscribeToSaveResponse(
                this.userPostOfficeService.create(this.userPostOffice));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<UserPostOffice>>) {
        result.subscribe((res: HttpResponse<UserPostOffice>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: UserPostOffice) {
        this.eventManager.broadcast({ name: 'user-post-office-detail.save.success', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.alertService.error(error.message, null, null);
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

