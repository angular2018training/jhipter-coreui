import { Component, OnInit,Input, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
    import { ActivatedRoute, Router } from '@angular/router';
    import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { QuotationGiveBack } from '../../shared/model/quotation-give-back.model';
import { QuotationGiveBackDetailPopupService } from './quotation-give-back-detail-popup.service';
import { QuotationGiveBackService } from '../../shared/service/quotation-give-back.service';
import { QuotationGiveBackDetailUpdateComponent} from './quotation-give-back-detail-update.component';
import { QuotationGiveBackDetailDeleteDialogComponent } from './quotation-give-back-detail-delete-dialog.component';


    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';
    import { DistrictType } from '../../shared/model/district-type.model';
    import { DistrictTypeService } from '../../shared/service/district-type.service';
    import { Region } from '../../shared/model/region.model';
    import { RegionService } from '../../shared/service/region.service';
import {AlertService} from "../../shared/alert/alert-service";


@Component({
    selector: 'jhi-quotation-give-back-detail-list',
    templateUrl: './quotation-give-back-detail-list.component.html'
})
export class QuotationGiveBackDetailListComponent implements OnInit, OnDestroy {

    currentAccount: any;
    quotationGiveBacks: QuotationGiveBack[];
    eventDeleteItemSubscriber: Subscription;
    eventUpdateItemSubscriber: Subscription;

    @Input() quotationParentId: number;
    constructor(
        private eventManager : JhiEventManager,
        private quotationGiveBackDetailPopupService :QuotationGiveBackDetailPopupService,
        private quotationGiveBackService: QuotationGiveBackService,
        private alertService :AlertService,

    ) {


    }

    createDetailForm(){
        this.quotationGiveBackDetailPopupService.open(QuotationGiveBackDetailUpdateComponent as Component,null,this.quotationParentId)
    }
    editDetailForm(quotationGiveBackId:number){
        this.quotationGiveBackDetailPopupService.open(QuotationGiveBackDetailUpdateComponent as Component,quotationGiveBackId,this.quotationParentId)
    }

    deleteDetailForm(quotationGiveBackId:number){
        this.quotationGiveBackDetailPopupService
          .open(QuotationGiveBackDetailDeleteDialogComponent as Component, quotationGiveBackId);
    }



    ngOnInit() {
        if(this.quotationParentId){
            this.quotationGiveBackService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationGiveBack[]>) => { this.quotationGiveBacks = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        }

        this.eventDeleteItemSubscriber = this.eventManager.subscribe('quotation-give-back-detail-delete-item',()=>{
            if(this.quotationParentId){
                this.quotationGiveBackService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationGiveBack[]>) => { this.quotationGiveBacks  = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            }
        });
        this.eventUpdateItemSubscriber = this.eventManager.subscribe('quotation-give-back-detail.save.success',()=>{
            if(this.quotationParentId){
                this.quotationGiveBackService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationGiveBack[]>) => { this.quotationGiveBacks = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
