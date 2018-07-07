import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { QuotationCod } from '../../shared/model/quotation-cod.model';
import { QuotationCodService } from '../../shared/service/quotation-cod.service';
import { CompanyService } from '../../shared/service/company.service';
import {Company  } from '../../shared/model/company.model';
import { DistrictTypeService } from '../../shared/service/district-type.service';
import {DistrictType  } from '../../shared/model/district-type.model';

@Component({
    selector: 'jhi-quotation-cod-detail-update',
    templateUrl: './quotation-cod-detail-update.component.html'
})
export class QuotationCodDetailUpdateComponent implements OnInit {

    quotationCod: QuotationCod;
    isSaving: boolean;

    companies: Company[];

    districttypes: DistrictType[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private quotationCodService: QuotationCodService,
        private companyService: CompanyService,
        private districtTypeService: DistrictTypeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.districtTypeService.query()
            .subscribe((res: HttpResponse<DistrictType[]>) => { this.districttypes = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.quotationCod.id !== undefined) {
            this.subscribeToSaveResponse(
                this.quotationCodService.update(this.quotationCod));
        } else {
            this.subscribeToSaveResponse(
                this.quotationCodService.create(this.quotationCod));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<QuotationCod>>) {
        result.subscribe((res: HttpResponse<QuotationCod>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: QuotationCod) {
        this.eventManager.broadcast({ name: 'quotation-cod-detail.save.success', content: 'OK'});
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
}

