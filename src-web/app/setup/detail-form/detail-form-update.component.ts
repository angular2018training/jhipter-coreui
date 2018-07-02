import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from '../../shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { DetailFormService } from './detail-form.service';

import { DetailForm } from './detail-form.model';
            import { Province, ProvinceService } from '../province';
            import { District, DistrictService } from '../district';
            import { MasterForm, MasterFormService } from '../master-form';

@Component({
    selector: 'jhi-detail-form-update',
    templateUrl: './detail-form-update.component.html'
})
export class DetailFormUpdateComponent implements OnInit {

    private _detailForm: DetailForm;
    isSaving: boolean;

    provinces: Province[];

    districts: District[];

    masterforms: MasterForm[];
        createDate: string;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private detailFormService: DetailFormService,
        private provinceService: ProvinceService,
        private districtService: DistrictService,
        private masterFormService: MasterFormService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({detailForm}) => {
            this.detailForm = detailForm;
        });
        this.provinceService.query()
            .subscribe((res: HttpResponse<Province[]>) => { this.provinces = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.districtService.query()
            .subscribe((res: HttpResponse<District[]>) => { this.districts = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.masterFormService.query()
            .subscribe((res: HttpResponse<MasterForm[]>) => { this.masterforms = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
                this.detailForm.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        if (this.detailForm.id !== undefined) {
            this.subscribeToSaveResponse(
                this.detailFormService.update(this.detailForm));
        } else {
            this.subscribeToSaveResponse(
                this.detailFormService.create(this.detailForm));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<DetailForm>>) {
        result.subscribe((res: HttpResponse<DetailForm>) =>
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

    trackMasterFormById(index: number, item: MasterForm) {
        return item.id;
    }
    get detailForm() {
        return this._detailForm;
    }

    set detailForm(detailForm: DetailForm) {
        this._detailForm = detailForm;
        this.createDate = moment(detailForm.createDate).format(DATE_TIME_FORMAT);
    }
}
