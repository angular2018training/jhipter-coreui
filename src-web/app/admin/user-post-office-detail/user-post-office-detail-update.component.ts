import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { UserPostOffice } from '../user-post-office/user-post-office.model';
import { UserPostOfficeService } from '../user-post-office/user-post-office.service';
import { Company, CompanyService } from '../company';
import { PostOffice, PostOfficeService } from '../post-office';
import { UserGroup, UserGroupService } from '../user-group';

@Component({
    selector: 'jhi-user-post-office-detail-update',
    templateUrl: './user-post-office-detail-update.component.html'
})
export class UserPostOfficeDetailUpdateComponent implements OnInit {

    userPostOffice: UserPostOffice;
    isSaving: boolean;

    companies: Company[];

    postoffices: PostOffice[];

    usergroups: UserGroup[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private userPostOfficeService: UserPostOfficeService,
        private companyService: CompanyService,
        private postOfficeService: PostOfficeService,
        private userGroupService: UserGroupService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postOfficeService.query()
            .subscribe((res: HttpResponse<PostOffice[]>) => { this.postoffices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.userGroupService.query()
            .subscribe((res: HttpResponse<UserGroup[]>) => { this.usergroups = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
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
        this.jhiAlertService.error(error.message, null, null);
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

