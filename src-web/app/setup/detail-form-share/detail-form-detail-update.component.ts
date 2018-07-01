import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from '../../shared/constants/input.constants';
import {JhiAlertService, JhiDataUtils, JhiEventManager} from 'ng-jhipster';
import {DetailForm} from "../detail-form/detail-form.model";
import {MasterForm} from "../master-form/master-form.model";
import {Province} from "../province/province.model";
import {District} from "../district/district.model";
import {DetailFormService} from "../detail-form/detail-form.service";
import {MasterFormService} from "../master-form/master-form.service";
import {ProvinceService} from "../province/province.service";
import {DistrictService} from "../district/district.service";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";


@Component({
    selector: 'jhi-detail-form-detail-update',
    templateUrl: './detail-form-detail-update.component.html'
})
export class DetailFormDetailUpdateComponent implements OnInit {

    private _detailForm: DetailForm;
    isSaving: boolean;

    provinces: Province[];

    districts: District[];
    createDate: string;

    constructor(
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private detailFormService: DetailFormService,
        private masterFormService: MasterFormService,
        private provinceService: ProvinceService,
        private districtService: DistrictService,
        private route: ActivatedRoute,
        private activeModal: NgbActiveModal,
        private eventManager: JhiEventManager,
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
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
        this.activeModal.dismiss();
        this.eventManager.broadcast({ name: 'detail-form-detail.save.success', content: 'OK'});
        //this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackMasterFormById(index: number, item: MasterForm) {
        return item.id;
    }

    trackProvinceById(index: number, item: Province) {
        return item.id;
    }

    trackDistrictById(index: number, item: District) {
        return item.id;
    }
    get detailForm() {
        return this._detailForm;
    }

    set detailForm(detailForm: DetailForm) {
        this._detailForm = detailForm;
        if(detailForm.createDate){
          this.createDate = moment(detailForm.createDate).format(DATE_TIME_FORMAT);
        }
    }
    cancel(){
      this.activeModal.dismiss();
    }
}
