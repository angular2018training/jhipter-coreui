import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { QuotationPickup } from '../../shared/model/quotation-pickup.model';
import { QuotationPickupService } from '../../shared/service/quotation-pickup.service';
import { CompanyService } from '../../shared/service/company.service';
import {Company  } from '../../shared/model/company.model';
import { ProvinceService } from '../../shared/service/province.service';
import {Province  } from '../../shared/model/province.model';
import { DistrictTypeService } from '../../shared/service/district-type.service';
import {DistrictType  } from '../../shared/model/district-type.model';

@Component({
    selector: 'jhi-quotation-pickup-detail-update',
    templateUrl: './quotation-pickup-detail-update.component.html'
})
export class QuotationPickupDetailUpdateComponent implements OnInit {

    quotationPickup: QuotationPickup;
    isSaving: boolean;

    companies: Company[];

    provinces: Province[];

    districttypes: DistrictType[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private quotationPickupService: QuotationPickupService,
        private companyService: CompanyService,
        private provinceService: ProvinceService,
        private districtTypeService: DistrictTypeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.provinceService.query()
            .subscribe((res: HttpResponse<Province[]>) => { this.provinces = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.districtTypeService.query()
            .subscribe((res: HttpResponse<DistrictType[]>) => { this.districttypes = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.quotationPickup.id !== undefined) {
            this.subscribeToSaveResponse(
                this.quotationPickupService.update(this.quotationPickup));
        } else {
            this.subscribeToSaveResponse(
                this.quotationPickupService.create(this.quotationPickup));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<QuotationPickup>>) {
        result.subscribe((res: HttpResponse<QuotationPickup>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: QuotationPickup) {
        this.eventManager.broadcast({ name: 'quotation-pickup-detail.save.success', content: 'OK'});
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

    trackProvinceById(index: number, item: Province) {
        return item.id;
    }

    trackDistrictTypeById(index: number, item: DistrictType) {
        return item.id;
    }
}

