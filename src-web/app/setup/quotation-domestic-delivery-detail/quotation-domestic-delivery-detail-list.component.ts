import { Component, OnInit,Input, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
    import { ActivatedRoute, Router } from '@angular/router';
    import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { QuotationDomesticDelivery } from '../../shared/model/quotation-domestic-delivery.model';
import { QuotationDomesticDeliveryDetailPopupService } from './quotation-domestic-delivery-detail-popup.service';
import { QuotationDomesticDeliveryService } from '../../shared/service/quotation-domestic-delivery.service';
import { QuotationDomesticDeliveryDetailUpdateComponent} from './quotation-domestic-delivery-detail-update.component';
import { QuotationDomesticDeliveryDetailDeleteDialogComponent } from './quotation-domestic-delivery-detail-delete-dialog.component';


    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';
    import { DistrictType } from '../../shared/model/district-type.model';
    import { DistrictTypeService } from '../../shared/service/district-type.service';
    import { Region } from '../../shared/model/region.model';
    import { RegionService } from '../../shared/service/region.service';
    import { Weight } from '../../shared/model/weight.model';
    import { WeightService } from '../../shared/service/weight.service';
import {AlertService} from "../../shared/alert/alert-service";


@Component({
    selector: 'jhi-quotation-domestic-delivery-detail-list',
    templateUrl: './quotation-domestic-delivery-detail-list.component.html'
})
export class QuotationDomesticDeliveryDetailListComponent implements OnInit, OnDestroy {

    currentAccount: any;
    quotationDomesticDeliveries: QuotationDomesticDelivery[];
    eventDeleteItemSubscriber: Subscription;
    eventUpdateItemSubscriber: Subscription;

    @Input() quotationParentId: number;
    constructor(
        private eventManager : JhiEventManager,
        private quotationDomesticDeliveryDetailPopupService :QuotationDomesticDeliveryDetailPopupService,
        private quotationDomesticDeliveryService: QuotationDomesticDeliveryService,
        private alertService :AlertService,

    ) {


    }

    createDetailForm(){
        this.quotationDomesticDeliveryDetailPopupService.open(QuotationDomesticDeliveryDetailUpdateComponent as Component,null,this.quotationParentId)
    }
    editDetailForm(quotationDomesticDeliveryId:number){
        this.quotationDomesticDeliveryDetailPopupService.open(QuotationDomesticDeliveryDetailUpdateComponent as Component,quotationDomesticDeliveryId,this.quotationParentId)
    }

    deleteDetailForm(quotationDomesticDeliveryId:number){
        this.quotationDomesticDeliveryDetailPopupService
          .open(QuotationDomesticDeliveryDetailDeleteDialogComponent as Component, quotationDomesticDeliveryId);
    }



    ngOnInit() {
        if(this.quotationParentId){
            this.quotationDomesticDeliveryService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationDomesticDelivery[]>) => { this.quotationDomesticDeliveries = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        }

        this.eventDeleteItemSubscriber = this.eventManager.subscribe('quotation-domestic-delivery-detail-delete-item',()=>{
            if(this.quotationParentId){
                this.quotationDomesticDeliveryService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationDomesticDelivery[]>) => { this.quotationDomesticDeliveries  = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            }
        });
        this.eventUpdateItemSubscriber = this.eventManager.subscribe('quotation-domestic-delivery-detail.save.success',()=>{
            if(this.quotationParentId){
                this.quotationDomesticDeliveryService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationDomesticDelivery[]>) => { this.quotationDomesticDeliveries = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
