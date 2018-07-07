import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { QuotationGiveBack } from '../../shared/model/quotation-give-back.model';
import { QuotationGiveBackService } from '../../shared/service/quotation-give-back.service';
import { CompanyService } from '../../shared/service/company.service';
import {Company  } from '../../shared/model/company.model';
import { DistrictTypeService } from '../../shared/service/district-type.service';
import {DistrictType  } from '../../shared/model/district-type.model';
import { RegionService } from '../../shared/service/region.service';
import {Region  } from '../../shared/model/region.model';

@Component({
    selector: 'jhi-quotation-give-back-detail-update',
    templateUrl: './quotation-give-back-detail-update.component.html'
})
export class QuotationGiveBackDetailUpdateComponent implements OnInit {

    quotationGiveBack: QuotationGiveBack;
    isSaving: boolean;

    companies: Company[];

    districttypes: DistrictType[];

    regions: Region[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private quotationGiveBackService: QuotationGiveBackService,
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
        if (this.quotationGiveBack.id !== undefined) {
            this.subscribeToSaveResponse(
                this.quotationGiveBackService.update(this.quotationGiveBack));
        } else {
            this.subscribeToSaveResponse(
                this.quotationGiveBackService.create(this.quotationGiveBack));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<QuotationGiveBack>>) {
        result.subscribe((res: HttpResponse<QuotationGiveBack>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: QuotationGiveBack) {
        this.eventManager.broadcast({ name: 'quotation-give-back-detail.save.success', content: 'OK'});
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

