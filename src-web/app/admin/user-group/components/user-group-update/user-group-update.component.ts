import { Component, OnInit, AfterContentInit, AfterViewInit, ViewEncapsulation, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, } from 'ng-jhipster';

import { UserGroupService } from '../../services/user-group.service';

import { UserGroup } from '../../models/user-group.model';
import { Company, CompanyService } from '../../../../setup/company';
import { UserService } from '../../../../shared/user/user.service';
import * as $ from 'jquery';
import 'select2';

@Component({
    selector: 'jhi-user-group-update',
    templateUrl: './user-group-update.component.html',
    encapsulation: ViewEncapsulation.None
})
export class UserGroupUpdateComponent implements OnInit, AfterViewInit {

    private _userGroup: UserGroup;
    isSaving: boolean;

    companies: Company[];

    authorities: any[];
    @ViewChild('selectAuthority') selectedView;
    constructor(
        private jhiAlertService: JhiAlertService,
        private userGroupService: UserGroupService,
        private companyService: CompanyService,
        private route: ActivatedRoute,
        private userService: UserService
    ) {

    }

    ngOnInit() {
        this.authorities = [];
        this.isSaving = false;
        this.route.data.subscribe(({ userGroup }) => {
            this.userGroup = userGroup;
        });
        this.userService.authorities().subscribe((authorities) => {
            this.authorities = authorities;
        });
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    ngAfterViewInit(): void {
        $('#select2id').select2({
            theme: 'bootstrap'
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.userGroup.companyId = 1;
        if (this.userGroup.id !== undefined) {
            this.subscribeToSaveResponse(
                this.userGroupService.update(this.userGroup));
        } else {
            this.subscribeToSaveResponse(
                this.userGroupService.create(this.userGroup));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<UserGroup>>) {
        result.subscribe((res: HttpResponse<UserGroup>) =>
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
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCompanyById(index: number, item: Company) {
        return item.id;
    }
    get userGroup() {
        return this._userGroup;
    }

    set userGroup(userGroup: UserGroup) {
        this._userGroup = userGroup;
    }
}
