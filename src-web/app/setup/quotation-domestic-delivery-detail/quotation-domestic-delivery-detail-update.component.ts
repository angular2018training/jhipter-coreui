import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { QuotationDomesticDelivery } from '../../shared/model/quotation-domestic-delivery.model';
import { QuotationDomesticDeliveryService } from '../../shared/service/quotation-domestic-delivery.service';
import { CompanyService } from '../../shared/service/company.service';
import {Company  } from '../../shared/model/company.model';
import { DistrictTypeService } from '../../shared/service/district-type.service';
import {DistrictType  } from '../../shared/model/district-type.model';
import { RegionService } from '../../shared/service/region.service';
import {Region  } from '../../shared/model/region.model';
import { WeightService } from '../../shared/service/weight.service';
import {Weight  } from '../../shared/model/weight.model';

@Component({
    selector: 'jhi-quotation-domestic-delivery-detail-update',
    templateUrl: './quotation-domestic-delivery-detail-update.component.html'
})
export class QuotationDomesticDeliveryDetailUpdateComponent implements OnInit {

    quotationDomesticDelivery: QuotationDomesticDelivery;
    isSaving: boolean;

    companies: Company[];

    districttypes: DistrictType[];

    regions: Region[];

    weights: Weight[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private quotationDomesticDeliveryService: QuotationDomesticDeliveryService,
        private companyService: CompanyService,
        private districtTypeService: DistrictTypeService,
        private regionService: RegionService,
        private weightService: WeightService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.districtTypeService.query()
            .subscribe((res: HttpResponse<DistrictType[]>) => { this.districttypes = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.regionService.query()
            .subscribe((res: HttpResponse<Region[]>) => { this.regions = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.weightService.query()
            .subscribe((res: HttpResponse<Weight[]>) => { this.weights = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.quotationDomesticDelivery.id !== undefined) {
            this.subscribeToSaveResponse(
                this.quotationDomesticDeliveryService.update(this.quotationDomesticDelivery));
        } else {
            this.subscribeToSaveResponse(
                this.quotationDomesticDeliveryService.create(this.quotationDomesticDelivery));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<QuotationDomesticDelivery>>) {
        result.subscribe((res: HttpResponse<QuotationDomesticDelivery>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: QuotationDomesticDelivery) {
        this.eventManager.broadcast({ name: 'quotation-domestic-delivery-detail.save.success', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
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
}

