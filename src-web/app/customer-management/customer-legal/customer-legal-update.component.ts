import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService, JhiEventManager, } from 'ng-jhipster';
import { Company } from '../../setup/company';
import { FileUpload } from '../../setup/file-upload';
import { CustomerLegal } from '../../shared/model/customer-legal.model';
import { CustomerLegalService } from '../../shared/service/customer-legal.service';
import { Province } from '../../shared/model/province.model';
import { District } from '../../shared/model/district.model';
import { ProvinceService } from '../../shared/service/province.service';
import { DistrictService } from '../../shared/service/district.service';

@Component({
    selector: 'jhi-customer-legal-update',
    templateUrl: './customer-legal-update.component.html'
})
export class CustomerLegalUpdateComponent implements OnInit {

    private _customerLegal: CustomerLegal;

    isSaving: boolean;

    provinces: Province[];

    districts: District[];
    customerId;
    constructor(
        private jhiAlertService: JhiAlertService,
        private customerLegalService: CustomerLegalService,
        private provinceService: ProvinceService,
        private districtService: DistrictService,
        private route: ActivatedRoute,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe((data) => {
            this.customerLegal = data.resolved.customerLegal;
            this.customerId = data.resolved.customerId;

            if (this.customerLegal.companyId) {
                this.getProvince(this.customerLegal.companyId);
                if (this.customerLegal.provinceId) {
                    this.getDistricts(this.customerLegal.provinceId);
                }
            }
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.customerLegal.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerLegalService.update(this.customerLegal, this.customerId));
        } else {

            this.customerLegalService.create(this.customerLegal, this.customerId).subscribe((res: HttpResponse<CustomerLegal>) => {
                this.isSaving = false;
                if (res.body) {
                    this.customerLegal.id = res.body.id;
                    this.eventManager.broadcast({
                        name: 'update-current-customer',
                        customer: {
                            legalId: this.customerLegal.id
                        }
                    });
                }
            }, (res: HttpErrorResponse) => this.onSaveError());
        }
    }

    onProvinceChanged(provinceId) {
        this.customerLegal.provinceId = provinceId;
        this.customerLegal.districtId = null;
        this.getDistricts(provinceId);
    }

    private getProvince(companyId) {
        this.districts = [];
        this.provinces = [];
        this.provinceService.query({
            'companyId.equals': companyId,
            size: 10000
        }).subscribe((res: HttpResponse<Province[]>) => { this.provinces = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    private getDistricts(provinceId) {
        this.districts = [];
        this.districtService.query({
            'provinceId.equals': provinceId,
            size: 10000
        }).subscribe((res: HttpResponse<District[]>) => { this.districts = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));

    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CustomerLegal>>) {
        result.subscribe((res: HttpResponse<CustomerLegal>) =>
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

    trackProvinceById(index: number, item: Province) {
        return item.id;
    }

    trackDistrictById(index: number, item: District) {
        return item.id;
    }

    trackFileUploadById(index: number, item: FileUpload) {
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

    get customerLegal() {
        return this._customerLegal;
    }

    set customerLegal(customerLegal: CustomerLegal) {
        this._customerLegal = customerLegal;
    }
}
