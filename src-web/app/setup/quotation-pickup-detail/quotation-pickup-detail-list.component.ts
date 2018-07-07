import { Component, OnInit,Input, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
    import { ActivatedRoute, Router } from '@angular/router';
    import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { QuotationPickup } from '../../shared/model/quotation-pickup.model';
import { QuotationPickupDetailPopupService } from './quotation-pickup-detail-popup.service';
import { QuotationPickupService } from '../../shared/service/quotation-pickup.service';
import { QuotationPickupDetailUpdateComponent} from './quotation-pickup-detail-update.component';
import { QuotationPickupDetailDeleteDialogComponent } from './quotation-pickup-detail-delete-dialog.component';


    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';
    import { Province } from '../../shared/model/province.model';
    import { ProvinceService } from '../../shared/service/province.service';
    import { DistrictType } from '../../shared/model/district-type.model';
    import { DistrictTypeService } from '../../shared/service/district-type.service';
import {AlertService} from "../../shared/alert/alert-service";


@Component({
    selector: 'jhi-quotation-pickup-detail-list',
    templateUrl: './quotation-pickup-detail-list.component.html'
})
export class QuotationPickupDetailListComponent implements OnInit, OnDestroy {

    currentAccount: any;
    quotationPickups: QuotationPickup[];
    eventDeleteItemSubscriber: Subscription;
    eventUpdateItemSubscriber: Subscription;

    @Input() quotationParentId: number;
    constructor(
        private eventManager : JhiEventManager,
        private quotationPickupDetailPopupService :QuotationPickupDetailPopupService,
        private quotationPickupService: QuotationPickupService,
        private alertService :AlertService,

    ) {


    }

    createDetailForm(){
        this.quotationPickupDetailPopupService.open(QuotationPickupDetailUpdateComponent as Component,null,this.quotationParentId)
    }
    editDetailForm(quotationPickupId:number){
        this.quotationPickupDetailPopupService.open(QuotationPickupDetailUpdateComponent as Component,quotationPickupId,this.quotationParentId)
    }

    deleteDetailForm(quotationPickupId:number){
        this.quotationPickupDetailPopupService
          .open(QuotationPickupDetailDeleteDialogComponent as Component, quotationPickupId);
    }



    ngOnInit() {
        if(this.quotationParentId){
            this.quotationPickupService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationPickup[]>) => { this.quotationPickups = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        }

        this.eventDeleteItemSubscriber = this.eventManager.subscribe('quotation-pickup-detail-delete-item',()=>{
            if(this.quotationParentId){
                this.quotationPickupService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationPickup[]>) => { this.quotationPickups  = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            }
        });
        this.eventUpdateItemSubscriber = this.eventManager.subscribe('quotation-pickup-detail.save.success',()=>{
            if(this.quotationParentId){
                this.quotationPickupService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationPickup[]>) => { this.quotationPickups = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
