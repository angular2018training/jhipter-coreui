import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { QuotationInsurance } from '../../shared/model/quotation-insurance.model';
import { QuotationInsuranceService } from '../../shared/service/quotation-insurance.service';
import { CompanyService } from '../../shared/service/company.service';
import {Company  } from '../../shared/model/company.model';
import { DistrictTypeService } from '../../shared/service/district-type.service';
import {DistrictType  } from '../../shared/model/district-type.model';

@Component({
    selector: 'jhi-quotation-insurance-detail-update',
    templateUrl: './quotation-insurance-detail-update.component.html'
})
export class QuotationInsuranceDetailUpdateComponent implements OnInit {

    quotationInsurance: QuotationInsurance;
    isSaving: boolean;

    companies: Company[];

    districttypes: DistrictType[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private quotationInsuranceService: QuotationInsuranceService,
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
        if (this.quotationInsurance.id !== undefined) {
            this.subscribeToSaveResponse(
                this.quotationInsuranceService.update(this.quotationInsurance));
        } else {
            this.subscribeToSaveResponse(
                this.quotationInsuranceService.create(this.quotationInsurance));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<QuotationInsurance>>) {
        result.subscribe((res: HttpResponse<QuotationInsurance>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: QuotationInsurance) {
        this.eventManager.broadcast({ name: 'quotation-insurance-detail.save.success', content: 'OK'});
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

