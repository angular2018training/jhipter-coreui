import { Component, OnInit,Input, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
    import { ActivatedRoute, Router } from '@angular/router';
    import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { QuotationSubServices } from '../../shared/model/quotation-sub-services.model';
import { QuotationSubServicesDetailPopupService } from './quotation-sub-services-detail-popup.service';
import { QuotationSubServicesService } from '../../shared/service/quotation-sub-services.service';
import { QuotationSubServicesDetailUpdateComponent} from './quotation-sub-services-detail-update.component';
import { QuotationSubServicesDetailDeleteDialogComponent } from './quotation-sub-services-detail-delete-dialog.component';


    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';
    import { OrderSubServicesType } from '../../shared/model/order-sub-services-type.model';
    import { OrderSubServicesTypeService } from '../../shared/service/order-sub-services-type.service';
    import { OrderSubServices } from '../../shared/model/order-sub-services.model';
    import { OrderSubServicesService } from '../../shared/service/order-sub-services.service';
import {AlertService} from "../../shared/alert/alert-service";


@Component({
    selector: 'jhi-quotation-sub-services-detail-list',
    templateUrl: './quotation-sub-services-detail-list.component.html'
})
export class QuotationSubServicesDetailListComponent implements OnInit, OnDestroy {

    currentAccount: any;
    quotationSubServices: QuotationSubServices[];
    eventDeleteItemSubscriber: Subscription;
    eventUpdateItemSubscriber: Subscription;

    @Input() quotationParentId: number;
    constructor(
        private eventManager : JhiEventManager,
        private quotationSubServicesDetailPopupService :QuotationSubServicesDetailPopupService,
        private quotationSubServicesService: QuotationSubServicesService,
        private alertService :AlertService,

    ) {


    }

    createDetailForm(){
        this.quotationSubServicesDetailPopupService.open(QuotationSubServicesDetailUpdateComponent as Component,null,this.quotationParentId)
    }
    editDetailForm(quotationSubServicesId:number){
        this.quotationSubServicesDetailPopupService.open(QuotationSubServicesDetailUpdateComponent as Component,quotationSubServicesId,this.quotationParentId)
    }

    deleteDetailForm(quotationSubServicesId:number){
        this.quotationSubServicesDetailPopupService
          .open(QuotationSubServicesDetailDeleteDialogComponent as Component, quotationSubServicesId);
    }



    ngOnInit() {
        if(this.quotationParentId){
            this.quotationSubServicesService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationSubServices[]>) => { this.quotationSubServices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        }

        this.eventDeleteItemSubscriber = this.eventManager.subscribe('quotation-sub-services-detail-delete-item',()=>{
            if(this.quotationParentId){
                this.quotationSubServicesService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationSubServices[]>) => { this.quotationSubServices  = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            }
        });
        this.eventUpdateItemSubscriber = this.eventManager.subscribe('quotation-sub-services-detail.save.success',()=>{
            if(this.quotationParentId){
                this.quotationSubServicesService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationSubServices[]>) => { this.quotationSubServices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
