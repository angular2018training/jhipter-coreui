
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { QuotationDomesticDeliveryService } from '../../shared/service/quotation-domestic-delivery.service';

import { QuotationDomesticDelivery } from '../../shared/model/quotation-domestic-delivery.model';
            import { Company } from '../../shared/model/company.model';
            import { CompanyService} from '../../shared/service/company.service';
            import { DistrictType } from '../../shared/model/district-type.model';
            import { DistrictTypeService} from '../../shared/service/district-type.service';
            import { Region } from '../../shared/model/region.model';
            import { RegionService} from '../../shared/service/region.service';
            import { Weight } from '../../shared/model/weight.model';
            import { WeightService} from '../../shared/service/weight.service';
            import { Quotation } from '../../shared/model/quotation.model';
            import { QuotationService} from '../../shared/service/quotation.service';

@Component({
    selector: 'jhi-quotation-domestic-delivery-update',
    templateUrl: './quotation-domestic-delivery-update.component.html'
})
export class QuotationDomesticDeliveryUpdateComponent implements OnInit {

    private _quotationDomesticDelivery: QuotationDomesticDelivery;
    isSaving: boolean;
    private currentAccount : any;

    districttypes: DistrictType[];

    regions: Region[];

    weights: Weight[];

    quotations: Quotation[];

    constructor(
        private alertService: AlertService,
        private quotationDomesticDeliveryService: QuotationDomesticDeliveryService,
        private principal : Principal,
        private companyService: CompanyService,
        private districtTypeService: DistrictTypeService,
        private regionService: RegionService,
        private weightService: WeightService,
        private quotationService: QuotationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({quotationDomesticDelivery}) => {
            this.quotationDomesticDelivery = quotationDomesticDelivery;
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.districtTypeService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<DistrictType[]>) => { this.districttypes = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            this.regionService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<Region[]>) => { this.regions = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            this.quotationService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<Quotation[]>) => { this.quotations = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        });
        this.weightService.query()
            .subscribe((res: HttpResponse<Weight[]>) => { this.weights = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));

    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.quotationDomesticDelivery.id !== undefined) {
            this.subscribeToSaveResponse(
                this.quotationDomesticDeliveryService.update(this.quotationDomesticDelivery));
        } else {
            this.quotationDomesticDelivery.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.quotationDomesticDeliveryService.create(this.quotationDomesticDelivery));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<QuotationDomesticDelivery>>) {
        result.subscribe((res: HttpResponse<QuotationDomesticDelivery>) =>
            this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.alertService.error(errorMessage, null, null);
    }

    trackCompanyById(index: number, item: Company) {
        return item.id;
    }

    trackDistrictTypeById(index: number, item: DistrictType) {
        return item.id;
    }

    trackRegionById(index: number, item: Region) {
        return item.id;
    }

    trackWeightById(index: number, item: Weight) {
        return item.id;
    }

    trackQuotationById(index: number, item: Quotation) {
        return item.id;
    }
    get quotationDomesticDelivery() {
        return this._quotationDomesticDelivery;
    }

    set quotationDomesticDelivery(quotationDomesticDelivery: QuotationDomesticDelivery) {
        this._quotationDomesticDelivery = quotationDomesticDelivery;
    }
}
