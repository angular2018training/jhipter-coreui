import { Component, OnInit,Input, OnDestroy,ViewChild } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
    import { ActivatedRoute, Router } from '@angular/router';
    import {NgForm} from '@angular/forms';
import { Subscription } from 'rxjs/Subscription';
import { JhiEventManager, JhiParseLinks, JhiAlertService } from 'ng-jhipster';

import { QuotationInsurance } from '../../shared/model/quotation-insurance.model';
import { QuotationInsuranceDetailPopupService } from './quotation-insurance-detail-popup.service';
import { QuotationInsuranceService } from '../../shared/service/quotation-insurance.service';
import { QuotationInsuranceDetailUpdateComponent} from './quotation-insurance-detail-update.component';
import { QuotationInsuranceDetailDeleteDialogComponent } from './quotation-insurance-detail-delete-dialog.component';


    import { Company } from '../../shared/model/company.model';
    import { CompanyService } from '../../shared/service/company.service';
    import { DistrictType } from '../../shared/model/district-type.model';
    import { DistrictTypeService } from '../../shared/service/district-type.service';
import {AlertService} from "../../shared/alert/alert-service";


@Component({
    selector: 'jhi-quotation-insurance-detail-list',
    templateUrl: './quotation-insurance-detail-list.component.html'
})
export class QuotationInsuranceDetailListComponent implements OnInit, OnDestroy {

    currentAccount: any;
    quotationInsurances: QuotationInsurance[];
    eventDeleteItemSubscriber: Subscription;
    eventUpdateItemSubscriber: Subscription;

    @Input() quotationParentId: number;
    constructor(
        private eventManager : JhiEventManager,
        private quotationInsuranceDetailPopupService :QuotationInsuranceDetailPopupService,
        private quotationInsuranceService: QuotationInsuranceService,
        private alertService :AlertService,

    ) {


    }

    createDetailForm(){
        this.quotationInsuranceDetailPopupService.open(QuotationInsuranceDetailUpdateComponent as Component,null,this.quotationParentId)
    }
    editDetailForm(quotationInsuranceId:number){
        this.quotationInsuranceDetailPopupService.open(QuotationInsuranceDetailUpdateComponent as Component,quotationInsuranceId,this.quotationParentId)
    }

    deleteDetailForm(quotationInsuranceId:number){
        this.quotationInsuranceDetailPopupService
          .open(QuotationInsuranceDetailDeleteDialogComponent as Component, quotationInsuranceId);
    }



    ngOnInit() {
        if(this.quotationParentId){
            this.quotationInsuranceService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationInsurance[]>) => { this.quotationInsurances = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        }

        this.eventDeleteItemSubscriber = this.eventManager.subscribe('quotation-insurance-detail-delete-item',()=>{
            if(this.quotationParentId){
                this.quotationInsuranceService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationInsurance[]>) => { this.quotationInsurances  = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            }
        });
        this.eventUpdateItemSubscriber = this.eventManager.subscribe('quotation-insurance-detail.save.success',()=>{
            if(this.quotationParentId){
                this.quotationInsuranceService.query({"quotationParentId.equals":this.quotationParentId, size: 10000}).subscribe((res: HttpResponse<QuotationInsurance[]>) => { this.quotationInsurances = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
