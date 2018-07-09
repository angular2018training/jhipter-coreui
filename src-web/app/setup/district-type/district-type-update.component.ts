
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { DistrictTypeService } from './district-type.service';

import { DistrictType } from './district-type.model';
            import { Company, CompanyService } from '../company';

@Component({
    selector: 'jhi-district-type-update',
    templateUrl: './district-type-update.component.html'
})
export class DistrictTypeUpdateComponent implements OnInit {

    private _districtType: DistrictType;
    isSaving: boolean;
    private currentAccount : any;

    constructor(
        private alertService: AlertService,
        private districtTypeService: DistrictTypeService,
        private principal : Principal,
        private companyService: CompanyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({districtType}) => {
            this.districtType = districtType;
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });

    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.districtType.id !== undefined) {
            this.subscribeToSaveResponse(
                this.districtTypeService.update(this.districtType));
        } else {
            this.districtType.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.districtTypeService.create(this.districtType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<DistrictType>>) {
        result.subscribe((res: HttpResponse<DistrictType>) =>
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
    get districtType() {
        return this._districtType;
    }

    set districtType(districtType: DistrictType) {
        this._districtType = districtType;
    }
}
