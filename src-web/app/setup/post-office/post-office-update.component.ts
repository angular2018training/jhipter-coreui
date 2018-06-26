import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { PostOfficeService } from './post-office.service';

import { PostOffice } from './post-office.model';
            import { Company, CompanyService } from '../company';
            import { Province, ProvinceService } from '../province';

@Component({
    selector: 'jhi-post-office-update',
    templateUrl: './post-office-update.component.html'
})
export class PostOfficeUpdateComponent implements OnInit {

    private _postOffice: PostOffice;
    isSaving: boolean;

    companies: Company[];

    provinces: Province[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private postOfficeService: PostOfficeService,
        private companyService: CompanyService,
        private provinceService: ProvinceService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({postOffice}) => {
            this.postOffice = postOffice;
        });
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.provinceService.query()
            .subscribe((res: HttpResponse<Province[]>) => { this.provinces = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.postOffice.id !== undefined) {
            this.subscribeToSaveResponse(
                this.postOfficeService.update(this.postOffice));
        } else {
            this.subscribeToSaveResponse(
                this.postOfficeService.create(this.postOffice));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<PostOffice>>) {
        result.subscribe((res: HttpResponse<PostOffice>) =>
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
    get postOffice() {
        return this._postOffice;
    }

    set postOffice(postOffice: PostOffice) {
        this._postOffice = postOffice;
    }
}
