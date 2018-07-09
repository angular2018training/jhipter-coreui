
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { PostOfficeService } from './post-office.service';

import { PostOffice } from './post-office.model';
            import { Company, CompanyService } from '../company';
            import { Province, ProvinceService } from '../province';
            import { District, DistrictService } from '../district';

@Component({
    selector: 'jhi-post-office-update',
    templateUrl: './post-office-update.component.html'
})
export class PostOfficeUpdateComponent implements OnInit {

    private _postOffice: PostOffice;
    isSaving: boolean;
    private currentAccount : any;

    provinces: Province[];

    districts: District[];

    constructor(
        private alertService: AlertService,
        private postOfficeService: PostOfficeService,
        private principal : Principal,
        private companyService: CompanyService,
        private provinceService: ProvinceService,
        private districtService: DistrictService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({postOffice}) => {
            this.postOffice = postOffice;
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.provinceService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<Province[]>) => { this.provinces = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            this.districtService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<District[]>) => { this.districts = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        });

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
            this.postOffice.companyId = this.currentAccount.companyId;
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
    get postOffice() {
        return this._postOffice;
    }

    set postOffice(postOffice: PostOffice) {
        this._postOffice = postOffice;
    }
}
