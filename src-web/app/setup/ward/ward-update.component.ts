import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { WardService } from './ward.service';

import { Ward } from './ward.model';
import {District} from "../../shared/model/district.model";
import {DistrictService} from "../../shared/service/district.service";

@Component({
    selector: 'jhi-ward-update',
    templateUrl: './ward-update.component.html'
})
export class WardUpdateComponent implements OnInit {

    private _ward: Ward;
    isSaving: boolean;

    districts: District[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private wardService: WardService,
        private districtService: DistrictService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({ward}) => {
            this.ward = ward;
        });
        this.districtService.query()
            .subscribe((res: HttpResponse<District[]>) => { this.districts = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
        this.jhiAlertService.error(errorMessage, null, null);
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
