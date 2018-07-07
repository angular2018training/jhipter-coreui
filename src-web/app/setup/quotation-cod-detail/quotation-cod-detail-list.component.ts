import { Component, OnInit,Input, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
    import { ActivatedRoute, Router } from '@angular/router';
    import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { QuotationCod } from '../../shared/model/quotation-cod.model';
import { QuotationCodDetailPopupService } from './quotation-cod-detail-popup.service';
import { QuotationCodService } from '../../shared/service/quotation-cod.service';
import { QuotationCodDetailUpdateComponent} from './quotation-cod-detail-update.component';
import { QuotationCodDetailDeleteDialogComponent } from './quotation-cod-detail-delete-dialog.component';


    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';
    import { DistrictType } from '../../shared/model/district-type.model';
    import { DistrictTypeService } from '../../shared/service/district-type.service';
import {AlertService} from "../../shared/alert/alert-service";


@Component({
    selector: 'jhi-quotation-cod-detail-list',
    templateUrl: './quotation-cod-detail-list.component.html'
})
export class QuotationCodDetailListComponent implements OnInit, OnDestroy {

    currentAccount: any;
    quotationCods: QuotationCod[];
    eventDeleteItemSubscriber: Subscription;
    eventUpdateItemSubscriber: Subscription;

    @Input() quotationParentId: number;
    constructor(
        private eventManager : JhiEventManager,
        private quotationCodDetailPopupService :QuotationCodDetailPopupService,
        private quotationCodService: QuotationCodService,
        private alertService :AlertService,

    ) {


    }

    createDetailForm(){
        this.quotationCodDetailPopupService.open(QuotationCodDetailUpdateComponent as Component,null,this.quotationParentId)
    }
    editDetailForm(quotationCodId:number){
        this.quotationCodDetailPopupService.open(QuotationCodDetailUpdateComponent as Component,quotationCodId,this.quotationParentId)
    }

    deleteDetailForm(quotationCodId:number){
        this.quotationCodDetailPopupService
          .open(QuotationCodDetailDeleteDialogComponent as Component, quotationCodId);
    }



    ngOnInit() {
        if(this.quotationParentId){
            this.quotationCodService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationCod[]>) => { this.quotationCods = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        }

        this.eventDeleteItemSubscriber = this.eventManager.subscribe('quotation-cod-detail-delete-item',()=>{
            if(this.quotationParentId){
                this.quotationCodService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationCod[]>) => { this.quotationCods  = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            }
        });
        this.eventUpdateItemSubscriber = this.eventManager.subscribe('quotation-cod-detail.save.success',()=>{
            if(this.quotationParentId){
                this.quotationCodService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationCod[]>) => { this.quotationCods = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            }
        });
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventDeleteItemSubscriber);
        this.eventManager.destroy(this.eventUpdateItemSubscriber);
    }


    private onError(error) {
        this.alertService.error(error.message, null, null);
    }

}
