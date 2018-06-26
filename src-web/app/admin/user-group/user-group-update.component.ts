import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { UserGroupService } from './user-group.service';

import { UserGroup } from './user-group.model';

@Component({
    selector: 'jhi-user-group-update',
    templateUrl: './user-group-update.component.html'
})
export class UserGroupUpdateComponent implements OnInit {

    private _userGroup: UserGroup;
    isSaving: boolean;

    constructor(
        private userGroupService: UserGroupService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({userGroup}) => {
            this.userGroup = userGroup;
        });
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
    get userGroup() {
        return this._userGroup;
    }

    set userGroup(userGroup: UserGroup) {
        this._userGroup = userGroup;
    }
}
