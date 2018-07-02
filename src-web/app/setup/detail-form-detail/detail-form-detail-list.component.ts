import { Component, OnInit,Input, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
    import { ActivatedRoute, Router } from '@angular/router';
    import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { DetailForm } from '../detail-form/detail-form.model';
import { DetailFormDetailPopupService } from './detail-form-detail-popup.service';
import { DetailFormService } from '../detail-form/detail-form.service';
import { DetailFormDetailUpdateComponent} from './detail-form-detail-update.component';
import { DetailFormDetailDeleteDialogComponent } from './detail-form-detail-delete-dialog.component';


    import { Province, ProvinceService } from '../province';
    import { District, DistrictService } from '../district';


@Component({
    selector: 'jhi-detail-form-detail-list',
    templateUrl: './detail-form-detail-list.component.html'
})
export class DetailFormDetailListComponent implements OnInit, OnDestroy {
    
    currentAccount: any;
    detailForms: DetailForm[];
    eventDeleteItemSubscriber: Subscription;
    eventUpdateItemSubscriber: Subscription;

    @Input() masterFormParentId: number;
    constructor(
        private eventManager : JhiEventManager,
        private detailFormDetailPopupService :DetailFormDetailPopupService,
        private detailFormService: DetailFormService,
        private jhiAlertService :JhiAlertService,
        private dataUtils: JhiDataUtils,

    ) {


    }

    createDetailForm(){
        this.detailFormDetailPopupService.open(DetailFormDetailUpdateComponent as Component,null,this.masterFormParentId)
    }
    editDetailForm(detailFormId:number){
        this.detailFormDetailPopupService.open(DetailFormDetailUpdateComponent as Component,detailFormId,this.masterFormParentId)
    }

    deleteDetailForm(detailFormId:number){
        this.detailFormDetailPopupService
          .open(DetailFormDetailDeleteDialogComponent as Component, detailFormId);
    }



    ngOnInit() {
        if(this.masterFormParentId){
            this.detailFormService.query({"masterFormParentId.equals":this.masterFormParentId, size: 10000}).subscribe((res: HttpResponse<DetailForm[]>) => { this.detailForms = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        }

        this.eventDeleteItemSubscriber = this.eventManager.subscribe('detail-form-detail-delete-item',()=>{
            if(this.masterFormParentId){
                this.detailFormService.query({"masterFormParentId.equals":this.masterFormParentId, size: 10000}).subscribe((res: HttpResponse<DetailForm[]>) => { this.detailForms  = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            }
        });
        this.eventUpdateItemSubscriber = this.eventManager.subscribe('detail-form-detail.save.success',()=>{
            if(this.masterFormParentId){
                this.detailFormService.query({"masterFormParentId.equals":this.masterFormParentId, size: 10000}).subscribe((res: HttpResponse<DetailForm[]>) => { this.detailForms = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            }
        });
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventDeleteItemSubscriber);
        this.eventManager.destroy(this.eventUpdateItemSubscriber);
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }
    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    private onError(error) {
        this.jhiAlertService.error(error.message, null, null);
    }

}
