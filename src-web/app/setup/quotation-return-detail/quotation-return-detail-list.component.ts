import { Component, OnInit,Input, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
    import { ActivatedRoute, Router } from '@angular/router';
    import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { QuotationReturn } from '../../shared/model/quotation-return.model';
import { QuotationReturnDetailPopupService } from './quotation-return-detail-popup.service';
import { QuotationReturnService } from '../../shared/service/quotation-return.service';
import { QuotationReturnDetailUpdateComponent} from './quotation-return-detail-update.component';
import { QuotationReturnDetailDeleteDialogComponent } from './quotation-return-detail-delete-dialog.component';


    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';
    import { DistrictType } from '../../shared/model/district-type.model';
    import { DistrictTypeService } from '../../shared/service/district-type.service';
    import { Region } from '../../shared/model/region.model';
    import { RegionService } from '../../shared/service/region.service';
import {AlertService} from "../../shared/alert/alert-service";


@Component({
    selector: 'jhi-quotation-return-detail-list',
    templateUrl: './quotation-return-detail-list.component.html'
})
export class QuotationReturnDetailListComponent implements OnInit, OnDestroy {

    currentAccount: any;
    quotationReturns: QuotationReturn[];
    eventDeleteItemSubscriber: Subscription;
    eventUpdateItemSubscriber: Subscription;

    @Input() quotationParentId: number;
    constructor(
        private eventManager : JhiEventManager,
        private quotationReturnDetailPopupService :QuotationReturnDetailPopupService,
        private quotationReturnService: QuotationReturnService,
        private alertService :AlertService,

    ) {


    }

    createDetailForm(){
        this.quotationReturnDetailPopupService.open(QuotationReturnDetailUpdateComponent as Component,null,this.quotationParentId)
    }
    editDetailForm(quotationReturnId:number){
        this.quotationReturnDetailPopupService.open(QuotationReturnDetailUpdateComponent as Component,quotationReturnId,this.quotationParentId)
    }

    deleteDetailForm(quotationReturnId:number){
        this.quotationReturnDetailPopupService
          .open(QuotationReturnDetailDeleteDialogComponent as Component, quotationReturnId);
    }



    ngOnInit() {
        if(this.quotationParentId){
            this.quotationReturnService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationReturn[]>) => { this.quotationReturns = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        }

        this.eventDeleteItemSubscriber = this.eventManager.subscribe('quotation-return-detail-delete-item',()=>{
            if(this.quotationParentId){
                this.quotationReturnService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationReturn[]>) => { this.quotationReturns  = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            }
        });
        this.eventUpdateItemSubscriber = this.eventManager.subscribe('quotation-return-detail.save.success',()=>{
            if(this.quotationParentId){
                this.quotationReturnService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationReturn[]>) => { this.quotationReturns = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
