import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { UserPositionService } from './user-position.service';

import { UserPosition } from './user-position.model';
            import { Company, CompanyService } from '../company';
            import { PostOffice, PostOfficeService } from '../post-office';
            import { Position, PositionService } from '../position';
            import { UserGroup, UserGroupService } from '../user-group';
            import { UserExtraInfo, UserExtraInfoService } from '../user-extra-info';

@Component({
    selector: 'jhi-user-position-update',
    templateUrl: './user-position-update.component.html'
})
export class UserPositionUpdateComponent implements OnInit {

    private _userPosition: UserPosition;
    isSaving: boolean;

    companies: Company[];

    postoffices: PostOffice[];

    positions: Position[];

    usergroups: UserGroup[];

    userextrainfos: UserExtraInfo[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private userPositionService: UserPositionService,
        private companyService: CompanyService,
        private postOfficeService: PostOfficeService,
        private positionService: PositionService,
        private userGroupService: UserGroupService,
        private userExtraInfoService: UserExtraInfoService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({userPosition}) => {
            this.userPosition = userPosition;
        });
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.postOfficeService.query()
            .subscribe((res: HttpResponse<PostOffice[]>) => { this.postoffices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.positionService.query()
            .subscribe((res: HttpResponse<Position[]>) => { this.positions = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.userGroupService.query()
            .subscribe((res: HttpResponse<UserGroup[]>) => { this.usergroups = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.userExtraInfoService.query()
            .subscribe((res: HttpResponse<UserExtraInfo[]>) => { this.userextrainfos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.userPosition.id !== undefined) {
            this.subscribeToSaveResponse(
                this.userPositionService.update(this.userPosition));
        } else {
            this.subscribeToSaveResponse(
                this.userPositionService.create(this.userPosition));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<UserPosition>>) {
        result.subscribe((res: HttpResponse<UserPosition>) =>
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

    trackPostOfficeById(index: number, item: PostOffice) {
        return item.id;
    }

    trackPositionById(index: number, item: Position) {
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
    get userPosition() {
        return this._userPosition;
    }

    set userPosition(userPosition: UserPosition) {
        this._userPosition = userPosition;
    }
}
