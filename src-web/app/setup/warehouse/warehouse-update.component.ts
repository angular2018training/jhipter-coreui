
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from '../../shared/constants/input.constants';
import { JhiAlertService,  } from 'ng-jhipster';

import { WarehouseService } from './warehouse.service';

import { Warehouse } from './warehouse.model';
            import { Company, CompanyService } from '../company';
            import { Province, ProvinceService } from '../province';
            import { District, DistrictService } from '../district';
            import { Ward, WardService } from '../ward';

@Component({
    selector: 'jhi-warehouse-update',
    templateUrl: './warehouse-update.component.html'
})
export class WarehouseUpdateComponent implements OnInit {

    private _warehouse: Warehouse;
    isSaving: boolean;
    private currentAccount : any;

    provinces: Province[];

    districts: District[];

    wards: Ward[];
        createDate: string;

    constructor(
        private alertService: AlertService,
        private warehouseService: WarehouseService,
        private principal : Principal,
        private companyService: CompanyService,
        private provinceService: ProvinceService,
        private districtService: DistrictService,
        private wardService: WardService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({warehouse}) => {
            this.warehouse = warehouse;
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.provinceService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<Province[]>) => { this.provinces = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            this.districtService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<District[]>) => { this.districts = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            this.wardService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<Ward[]>) => { this.wards = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        });

    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
                this.warehouse.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        if (this.warehouse.id !== undefined) {
            this.subscribeToSaveResponse(
                this.warehouseService.update(this.warehouse));
        } else {
            this.warehouse.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.warehouseService.create(this.warehouse));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Warehouse>>) {
        result.subscribe((res: HttpResponse<Warehouse>) =>
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

    trackDistrictById(index: number, item: District) {
        return item.id;
    }

    trackWardById(index: number, item: Ward) {
        return item.id;
    }
    get warehouse() {
        return this._warehouse;
    }

    set warehouse(warehouse: Warehouse) {
        this._warehouse = warehouse;
        this.createDate = moment(warehouse.createDate).format(DATE_TIME_FORMAT);
    }
}
