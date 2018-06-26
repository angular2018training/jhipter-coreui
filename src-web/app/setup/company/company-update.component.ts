import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { CompanyService } from './company.service';

import { Company } from './company.model';
            import { Province, ProvinceService } from '../province';
            import { District, DistrictService } from '../district';

@Component({
    selector: 'jhi-company-update',
    templateUrl: './company-update.component.html'
})
export class CompanyUpdateComponent implements OnInit {

    private _company: Company;
    isSaving: boolean;

    provinces: Province[];

    districts: District[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private companyService: CompanyService,
        private provinceService: ProvinceService,
        private districtService: DistrictService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({company}) => {
            this.company = company;
        });
        this.provinceService.query()
            .subscribe((res: HttpResponse<Province[]>) => { this.provinces = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.districtService.query()
            .subscribe((res: HttpResponse<District[]>) => { this.districts = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.company.id !== undefined) {
            this.subscribeToSaveResponse(
                this.companyService.update(this.company));
        } else {
            this.subscribeToSaveResponse(
                this.companyService.create(this.company));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Company>>) {
        result.subscribe((res: HttpResponse<Company>) =>
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

    trackProvinceById(index: number, item: Province) {
        return item.id;
    }

    trackDistrictById(index: number, item: District) {
        return item.id;
    }
    get company() {
        return this._company;
    }

    set company(company: Company) {
        this._company = company;
    }
}
