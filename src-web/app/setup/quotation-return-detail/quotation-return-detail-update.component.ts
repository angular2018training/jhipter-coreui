import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { QuotationReturn } from '../../shared/model/quotation-return.model';
import { QuotationReturnService } from '../../shared/service/quotation-return.service';
import { CompanyService } from '../../shared/service/company.service';
import {Company  } from '../../shared/model/company.model';
import { DistrictTypeService } from '../../shared/service/district-type.service';
import {DistrictType  } from '../../shared/model/district-type.model';
import { RegionService } from '../../shared/service/region.service';
import {Region  } from '../../shared/model/region.model';

@Component({
    selector: 'jhi-quotation-return-detail-update',
    templateUrl: './quotation-return-detail-update.component.html'
})
export class QuotationReturnDetailUpdateComponent implements OnInit {

    quotationReturn: QuotationReturn;
    isSaving: boolean;

    companies: Company[];

    districttypes: DistrictType[];

    regions: Region[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private quotationReturnService: QuotationReturnService,
        private companyService: CompanyService,
        private districtTypeService: DistrictTypeService,
        private regionService: RegionService,
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
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.quotationReturn.id !== undefined) {
            this.subscribeToSaveResponse(
                this.quotationReturnService.update(this.quotationReturn));
        } else {
            this.subscribeToSaveResponse(
                this.quotationReturnService.create(this.quotationReturn));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<QuotationReturn>>) {
        result.subscribe((res: HttpResponse<QuotationReturn>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: QuotationReturn) {
        this.eventManager.broadcast({ name: 'quotation-return-detail.save.success', content: 'OK'});
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
}

