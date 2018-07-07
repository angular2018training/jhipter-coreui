
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { QuotationCodService } from '../../shared/service/quotation-cod.service';

import { QuotationCod } from '../../shared/model/quotation-cod.model';
            import { Company } from '../../shared/model/company.model';
            import { CompanyService} from '../../shared/service/company.service';
            import { DistrictType } from '../../shared/model/district-type.model';
            import { DistrictTypeService} from '../../shared/service/district-type.service';
            import { Quotation } from '../../shared/model/quotation.model';
            import { QuotationService} from '../../shared/service/quotation.service';

@Component({
    selector: 'jhi-quotation-cod-update',
    templateUrl: './quotation-cod-update.component.html'
})
export class QuotationCodUpdateComponent implements OnInit {

    private _quotationCod: QuotationCod;
    isSaving: boolean;
    private currentAccount : any;

    districttypes: DistrictType[];

    quotations: Quotation[];

    constructor(
        private alertService: AlertService,
        private quotationCodService: QuotationCodService,
        private principal : Principal,
        private companyService: CompanyService,
        private districtTypeService: DistrictTypeService,
        private quotationService: QuotationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({quotationCod}) => {
            this.quotationCod = quotationCod;
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.districtTypeService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<DistrictType[]>) => { this.districttypes = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
            this.quotationService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<Quotation[]>) => { this.quotations = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        });

    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.quotationCod.id !== undefined) {
            this.subscribeToSaveResponse(
                this.quotationCodService.update(this.quotationCod));
        } else {
            this.quotationCod.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.quotationCodService.create(this.quotationCod));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<QuotationCod>>) {
        result.subscribe((res: HttpResponse<QuotationCod>) =>
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

    trackQuotationById(index: number, item: Quotation) {
        return item.id;
    }
    get quotationCod() {
        return this._quotationCod;
    }

    set quotationCod(quotationCod: QuotationCod) {
        this._quotationCod = quotationCod;
    }
}
