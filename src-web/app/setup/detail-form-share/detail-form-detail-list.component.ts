import {Component, Input, OnDestroy, OnInit} from "@angular/core";
import {JhiAlertService, JhiDataUtils, JhiEventManager} from "ng-jhipster";

import {Subscription} from "rxjs/Rx";
import {DetailForm} from "../detail-form/detail-form.model";
import {DetailFormDetailDeleteDialogComponent} from "./detail-form-detail-delete-dialog.component";
import {DetailFormDetailUpdateComponent} from "./detail-form-detail-update.component";
import {DetailFormDetailPopupService} from "./detail-form-detail-popup.service";
import {DetailFormService} from "../detail-form/detail-form.service";
import {HttpErrorResponse, HttpResponse} from "@angular/common/http";
@Component({
  selector: 'jhi-detail-form-detail-list',
  templateUrl: './detail-form-detail-list.component.html'
})
export class DetailFormDetailListComponent implements OnInit, OnDestroy {
  @Input() masterFormId: number;
  detailForms : DetailForm[];
  eventSubscriber: Subscription;
  constructor(private eventManager : JhiEventManager,
              private detailFormDetailPopupService :DetailFormDetailPopupService,
              private detailFormService: DetailFormService,
              private jhiAlertService :JhiAlertService,
              private dataUtils: JhiDataUtils

  ){

  }
  ngOnInit() {
    if(this.masterFormId){
      this.detailFormService.query({"masterFormId.equals":this.masterFormId, size: 10000}).subscribe((res: HttpResponse<DetailForm[]>) => { this.detailForms = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }
    this.eventManager.subscribe('detail-form-detail-delete-item',()=>{
      if(this.masterFormId){
        this.detailFormService.query({"masterFormId.equals":this.masterFormId, size: 10000}).subscribe((res: HttpResponse<DetailForm[]>) => { this.detailForms = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
      }
    });
    this.eventManager.subscribe('detail-form-detail.save.success',()=>{
      if(this.masterFormId){
        this.detailFormService.query({"masterFormId.equals":this.masterFormId, size: 10000}).subscribe((res: HttpResponse<DetailForm[]>) => { this.detailForms = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
      }
    });


  }
  ngOnDestroy() {
    this.eventManager.destroy(this.eventSubscriber);
  }

  createDetailForm(){
    this.detailFormDetailPopupService.open(DetailFormDetailUpdateComponent as Component,null,this.masterFormId)
  }
  editDetailForm(detailFormId:number){
    this.detailFormDetailPopupService.open(DetailFormDetailUpdateComponent as Component,detailFormId,this.masterFormId)
  }

  deleteDetailForm(detailFormId:number){
    this.detailFormDetailPopupService
      .open(DetailFormDetailDeleteDialogComponent as Component, detailFormId);
  }
  private onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }


  byteSize(field) {
    return this.dataUtils.byteSize(field);
  }

  openFile(contentType, field) {
    return this.dataUtils.openFile(contentType, field);
  }


}
