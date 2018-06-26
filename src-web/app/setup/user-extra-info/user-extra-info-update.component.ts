import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from '../../shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { UserExtraInfoService } from './user-extra-info.service';

import { UserExtraInfo } from './user-extra-info.model';
            import { Company, CompanyService } from '../company';

@Component({
    selector: 'jhi-user-extra-info-update',
    templateUrl: './user-extra-info-update.component.html'
})
export class UserExtraInfoUpdateComponent implements OnInit {

    private _userExtraInfo: UserExtraInfo;
    isSaving: boolean;

    companies: Company[];
    validDate: string;
    lastLoginDate: string;
    contractExpirationDate: string;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private userExtraInfoService: UserExtraInfoService,
        private companyService: CompanyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({userExtraInfo}) => {
            this.userExtraInfo = userExtraInfo;
        });
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
                this.userExtraInfo.validDate = moment(this.validDate, DATE_TIME_FORMAT);
                this.userExtraInfo.lastLoginDate = moment(this.lastLoginDate, DATE_TIME_FORMAT);
                this.userExtraInfo.contractExpirationDate = moment(this.contractExpirationDate, DATE_TIME_FORMAT);
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
    get userExtraInfo() {
        return this._userExtraInfo;
    }

    set userExtraInfo(userExtraInfo: UserExtraInfo) {
        this._userExtraInfo = userExtraInfo;
        this.validDate = moment(userExtraInfo.validDate).format(DATE_TIME_FORMAT);
        this.lastLoginDate = moment(userExtraInfo.lastLoginDate).format(DATE_TIME_FORMAT);
        this.contractExpirationDate = moment(userExtraInfo.contractExpirationDate).format(DATE_TIME_FORMAT);
    }
}
