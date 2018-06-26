import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { CustomerLegalService } from './customer-legal.service';

import { CustomerLegal } from './customer-legal.model';
            import { Company, CompanyService } from '../../setup/company';
            import { Province, ProvinceService } from '../../setup/province';
            import { District, DistrictService } from '../../setup/district';
            import { FileUpload, FileUploadService } from '../../setup/file-upload';

@Component({
    selector: 'jhi-customer-legal-update',
    templateUrl: './customer-legal-update.component.html'
})
export class CustomerLegalUpdateComponent implements OnInit {

    private _customerLegal: CustomerLegal;
    isSaving: boolean;

    companies: Company[];

    provinces: Province[];

    districts: District[];

    fileuploads: FileUpload[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private customerLegalService: CustomerLegalService,
        private companyService: CompanyService,
        private provinceService: ProvinceService,
        private districtService: DistrictService,
        private fileUploadService: FileUploadService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({customerLegal}) => {
            this.customerLegal = customerLegal;
        });
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.provinceService.query()
            .subscribe((res: HttpResponse<Province[]>) => { this.provinces = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.districtService.query()
            .subscribe((res: HttpResponse<District[]>) => { this.districts = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.fileUploadService.query()
            .subscribe((res: HttpResponse<FileUpload[]>) => { this.fileuploads = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.customerLegal.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerLegalService.update(this.customerLegal));
        } else {
            this.subscribeToSaveResponse(
                this.customerLegalService.create(this.customerLegal));
        }
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
