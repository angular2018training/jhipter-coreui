
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { ProvinceService } from './province.service';

import { Province } from './province.model';
            import { Company, CompanyService } from '../company';

@Component({
    selector: 'jhi-province-update',
    templateUrl: './province-update.component.html'
})
export class ProvinceUpdateComponent implements OnInit {

    private _province: Province;
    isSaving: boolean;
    private currentAccount : any;

    constructor(
        private alertService: AlertService,
        private provinceService: ProvinceService,
        private principal : Principal,
        private companyService: CompanyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({province}) => {
            this.province = province;
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
        if (this.province.id !== undefined) {
            this.subscribeToSaveResponse(
                this.provinceService.update(this.province));
        } else {
            this.province.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.provinceService.create(this.province));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Province>>) {
        result.subscribe((res: HttpResponse<Province>) =>
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
    get province() {
        return this._province;
    }

    set province(province: Province) {
        this._province = province;
    }
}
