
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { WardService } from './ward.service';

import { Ward } from './ward.model';
            import { Company, CompanyService } from '../company';
            import { District, DistrictService } from '../district';

@Component({
    selector: 'jhi-ward-update',
    templateUrl: './ward-update.component.html'
})
export class WardUpdateComponent implements OnInit {

    private _ward: Ward;
    isSaving: boolean;
    private currentAccount : any;

    districts: District[];

    constructor(
        private alertService: AlertService,
        private wardService: WardService,
        private principal : Principal,
        private companyService: CompanyService,
        private districtService: DistrictService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({ward}) => {
            this.ward = ward;
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.districtService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<District[]>) => { this.districts = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        });

    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.ward.id !== undefined) {
            this.subscribeToSaveResponse(
                this.wardService.update(this.ward));
        } else {
            this.ward.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.wardService.create(this.ward));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Ward>>) {
        result.subscribe((res: HttpResponse<Ward>) =>
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

    trackDistrictById(index: number, item: District) {
        return item.id;
    }
    get ward() {
        return this._ward;
    }

    set ward(ward: Ward) {
        this._ward = ward;
    }
}
