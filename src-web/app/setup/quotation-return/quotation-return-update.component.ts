
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { QuotationReturnService } from '../../shared/service/quotation-return.service';

import { QuotationReturn } from '../../shared/model/quotation-return.model';
            import { Company } from '../../shared/model/company.model';
            import { CompanyService} from '../../shared/service/company.service';
            import { DistrictType } from '../../shared/model/district-type.model';
            import { DistrictTypeService} from '../../shared/service/district-type.service';
            import { Region } from '../../shared/model/region.model';
            import { RegionService} from '../../shared/service/region.service';
            import { Quotation } from '../../shared/model/quotation.model';
            import { QuotationService} from '../../shared/service/quotation.service';

@Component({
    selector: 'jhi-quotation-return-update',
    templateUrl: './quotation-return-update.component.html'
})
export class QuotationReturnUpdateComponent implements OnInit {

    private _quotationReturn: QuotationReturn;
    isSaving: boolean;
    private currentAccount : any;

    districttypes: DistrictType[];

    regions: Region[];

    quotations: Quotation[];

    constructor(
        private alertService: AlertService,
        private quotationReturnService: QuotationReturnService,
        private principal : Principal,
        private companyService: CompanyService,
        private districtTypeService: DistrictTypeService,
        private regionService: RegionService,
        private quotationService: QuotationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({quotationReturn}) => {
            this.quotationReturn = quotationReturn;
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

    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.quotationReturn.id !== undefined) {
            this.subscribeToSaveResponse(
                this.quotationReturnService.update(this.quotationReturn));
        } else {
            this.quotationReturn.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.quotationReturnService.create(this.quotationReturn));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<QuotationReturn>>) {
        result.subscribe((res: HttpResponse<QuotationReturn>) =>
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

    trackQuotationById(index: number, item: Quotation) {
        return item.id;
    }
    get quotationReturn() {
        return this._quotationReturn;
    }

    set quotationReturn(quotationReturn: QuotationReturn) {
        this._quotationReturn = quotationReturn;
    }
}
