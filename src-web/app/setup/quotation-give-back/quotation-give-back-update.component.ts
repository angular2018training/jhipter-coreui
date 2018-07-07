
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { QuotationGiveBackService } from '../../shared/service/quotation-give-back.service';

import { QuotationGiveBack } from '../../shared/model/quotation-give-back.model';
            import { Company } from '../../shared/model/company.model';
            import { CompanyService} from '../../shared/service/company.service';
            import { DistrictType } from '../../shared/model/district-type.model';
            import { DistrictTypeService} from '../../shared/service/district-type.service';
            import { Region } from '../../shared/model/region.model';
            import { RegionService} from '../../shared/service/region.service';
            import { Quotation } from '../../shared/model/quotation.model';
            import { QuotationService} from '../../shared/service/quotation.service';

@Component({
    selector: 'jhi-quotation-give-back-update',
    templateUrl: './quotation-give-back-update.component.html'
})
export class QuotationGiveBackUpdateComponent implements OnInit {

    private _quotationGiveBack: QuotationGiveBack;
    isSaving: boolean;
    private currentAccount : any;

    districttypes: DistrictType[];

    regions: Region[];

    quotations: Quotation[];

    constructor(
        private alertService: AlertService,
        private quotationGiveBackService: QuotationGiveBackService,
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
        this.route.data.subscribe(({quotationGiveBack}) => {
            this.quotationGiveBack = quotationGiveBack;
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
        if (this.quotationGiveBack.id !== undefined) {
            this.subscribeToSaveResponse(
                this.quotationGiveBackService.update(this.quotationGiveBack));
        } else {
            this.quotationGiveBack.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.quotationGiveBackService.create(this.quotationGiveBack));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<QuotationGiveBack>>) {
        result.subscribe((res: HttpResponse<QuotationGiveBack>) =>
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
    get quotationGiveBack() {
        return this._quotationGiveBack;
    }

    set quotationGiveBack(quotationGiveBack: QuotationGiveBack) {
        this._quotationGiveBack = quotationGiveBack;
    }
}
