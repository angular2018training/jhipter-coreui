import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from '../../shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { MasterFormService } from './master-form.service';

import { MasterForm } from './master-form.model';
            import { Province, ProvinceService } from '../province';
            import { District, DistrictService } from '../district';

@Component({
    selector: 'jhi-master-form-update',
    templateUrl: './master-form-update.component.html'
})
export class MasterFormUpdateComponent implements OnInit {

    private _masterForm: MasterForm;
    isSaving: boolean;

    provinces: Province[];

    districts: District[];
    receiveTime: string;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private masterFormService: MasterFormService,
        private provinceService: ProvinceService,
        private districtService: DistrictService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({masterForm}) => {
            this.masterForm = masterForm;
        });
        this.provinceService.query()
            .subscribe((res: HttpResponse<Province[]>) => { this.provinces = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.districtService.query()
            .subscribe((res: HttpResponse<District[]>) => { this.districts = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
                this.masterForm.receiveTime = moment(this.receiveTime, DATE_TIME_FORMAT);
        if (this.masterForm.id !== undefined) {
            this.subscribeToSaveResponse(
                this.masterFormService.update(this.masterForm));
        } else {
            this.subscribeToSaveResponse(
                this.masterFormService.create(this.masterForm));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<MasterForm>>) {
        result.subscribe((res: HttpResponse<MasterForm>) =>
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
    get masterForm() {
        return this._masterForm;
    }

    set masterForm(masterForm: MasterForm) {
        this._masterForm = masterForm;
        this.receiveTime = moment(masterForm.receiveTime).format(DATE_TIME_FORMAT);
    }
}
