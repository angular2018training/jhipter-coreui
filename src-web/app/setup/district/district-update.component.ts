
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { DistrictService } from './district.service';

import { District } from './district.model';
            import { Company, CompanyService } from '../company';
            import { Province, ProvinceService } from '../province';
            import { DistrictType, DistrictTypeService } from '../district-type';

@Component({
    selector: 'jhi-district-update',
    templateUrl: './district-update.component.html'
})
export class DistrictUpdateComponent implements OnInit {

    private _district: District;
    isSaving: boolean;
    private currentAccount : any;

    provinces: Province[];

    districttypes: DistrictType[];

    constructor(
        private alertService: AlertService,
        private districtService: DistrictService,
        private principal : Principal,
        private companyService: CompanyService,
        private provinceService: ProvinceService,
        private districtTypeService: DistrictTypeService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({district}) => {
            this.district = district;
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.provinceService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<Province[]>) => { this.provinces = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            this.districtTypeService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<DistrictType[]>) => { this.districttypes = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        });

    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.district.id !== undefined) {
            this.subscribeToSaveResponse(
                this.districtService.update(this.district));
        } else {
            this.district.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.districtService.create(this.district));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<District>>) {
        result.subscribe((res: HttpResponse<District>) =>
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

    trackProvinceById(index: number, item: Province) {
        return item.id;
    }

    trackDistrictTypeById(index: number, item: DistrictType) {
        return item.id;
    }
    get district() {
        return this._district;
    }

    set district(district: District) {
        this._district = district;
    }
}
