import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { UserGroupService } from './user-group.service';

import { UserGroup } from './user-group.model';
            import { Company, CompanyService } from '../../setup/company';
import {UserService} from "../../shared/user/user.service";

@Component({
    selector: 'jhi-user-group-update',
    templateUrl: './user-group-update.component.html'
})
export class UserGroupUpdateComponent implements OnInit {

    private _userGroup: UserGroup;
    isSaving: boolean;

    companies: Company[];

    authorities: any[];
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
        this.route.data.subscribe(({userGroup}) => {
            this.userGroup = userGroup;

        });
      this.userService.authorities().subscribe(authorities => {
        this.authorities = authorities;
      });
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
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
