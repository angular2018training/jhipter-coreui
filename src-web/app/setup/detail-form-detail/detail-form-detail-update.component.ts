import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';
    import * as moment from 'moment';
    import { DATE_TIME_FORMAT } from '../../shared/constants/input.constants';
import { DetailForm } from '../detail-form/detail-form.model';
import { DetailFormService } from '../detail-form/detail-form.service';
import { Province, ProvinceService } from '../province';
import { District, DistrictService } from '../district';

@Component({
    selector: 'jhi-detail-form-detail-update',
    templateUrl: './detail-form-detail-update.component.html'
})
export class DetailFormDetailUpdateComponent implements OnInit {

    detailForm: DetailForm;
    isSaving: boolean;

    provinces: Province[];

    districts: District[];
        createDate: string;

    constructor(
        public activeModal: NgbActiveModal,
        private dataUtils: JhiDataUtils,
        private jhiAlertService: JhiAlertService,
        private detailFormService: DetailFormService,
        private provinceService: ProvinceService,
        private districtService: DistrictService,
        private eventManager: JhiEventManager
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

    clear() {
        this.activeModal.dismiss('cancel');
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
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: DetailForm) {
        this.eventManager.broadcast({ name: 'detail-form-detail.save.success', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackProvinceById(index: number, item: Province) {
        return item.id;
    }

    trackDistrictById(index: number, item: District) {
        return item.id;
    }
}

