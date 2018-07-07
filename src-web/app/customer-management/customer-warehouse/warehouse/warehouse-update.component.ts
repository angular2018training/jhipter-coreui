import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { JhiAlertService, JhiEventManager, } from 'ng-jhipster';
import { Warehouse } from '../../../shared/model/warehouse.model';
import { Province } from '../../../shared/model/province.model';
import { District } from '../../../shared/model/district.model';
import { Ward } from '../../../shared/model/ward.model';
import { WarehouseService } from '../../../shared/service/warehouse.service';
import { ProvinceService } from '../../../shared/service/province.service';
import { WardService } from '../../../shared/service/ward.service';
import { DistrictService } from '../../../shared/service/district.service';
import { DATE_TIME_FORMAT } from '../../../shared/constants/input.constants';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { CustomerWarehouse } from '../../../shared/model/customer-warehouse.model';
import { CustomerWarehouseWrapperModel } from '../../../shared/model/customer-warehouse-wrapper.model';
import { CustomerWarehouseService } from '../../../shared/service/customer-warehouse.service';
@Component({
    selector: 'jhi-warehouse-update',
    templateUrl: './warehouse-update.component.html'
})
export class WarehouseUpdateComponent implements OnInit {

    private _warehouse: Warehouse;

    customerWarehouse: CustomerWarehouse;

    isSaving: boolean;

    provinces: Province[];

    districts: District[];

    wards: Ward[];

    createDate: string;

    bsConfig = { dateInputFormat: 'DD/MM/YYYY' };

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private provinceService: ProvinceService,
        private districtService: DistrictService,
        private wardService: WardService,
        private customerWarehouseService: CustomerWarehouseService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        const wrapper = <CustomerWarehouseWrapperModel>{
            warehouseDTO: { ...this.warehouse },
            customerWarehouseDTO: this.customerWarehouse
        };
        // wrapper.warehouseDTO.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        if (this.warehouse.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerWarehouseService.update(wrapper));
        } else {
            this.subscribeToSaveResponse(
                this.customerWarehouseService.create(wrapper));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Warehouse>>) {
        result.subscribe((res: HttpResponse<Warehouse>) =>
            this.onSaveSuccess(res), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result) {
        this.isSaving = false;
        this.eventManager.broadcast({ name: 'customerWarehouseListModification', content: 'OK' });
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
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

    onProvinceChanged(provinceId) {
        this.warehouse.provinceId = provinceId;
        this.warehouse.districtId = null;
        this.warehouse.wardId = null;
        this.getDistricts(provinceId);
    }

    onDistrictChanged(districtId) {
        this.warehouse.districtId = districtId;
        this.warehouse.wardId = null;
        this.getWards(districtId);
    }

    private getProvince(companyId) {
        this.districts = [];
        this.provinces = [];
        this.provinceService.query({
            'companyId.equals': companyId,
            size: 10000
        }).subscribe(
            (res: HttpResponse<Province[]>) => { this.provinces = res.body; },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    private getDistricts(provinceId) {
        this.districts = [];
        this.districtService.query({
            'provinceId.equals': provinceId,
            size: 10000
        }).subscribe(
            (res: HttpResponse<District[]>) => { this.districts = res.body; },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    private getWards(districtId) {
        this.wardService.query({
            'districtId.equals': districtId
        }).subscribe(
            (res: HttpResponse<Ward[]>) => { this.wards = res.body; },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }
    get warehouse() {
        return this._warehouse;
    }

    set warehouse(warehouse: Warehouse) {
        debugger;
        if (!warehouse.createDate) {
            warehouse.createDate = new Date();
        }
        if (warehouse.companyId) {
            this.getProvince(warehouse.companyId);
            if (warehouse.provinceId) {
                this.getDistricts(warehouse.provinceId);
                if (warehouse.wardId) {
                    this.getWards(warehouse.districtId);
                }
            }
        }
        this._warehouse = warehouse;
    }
}
