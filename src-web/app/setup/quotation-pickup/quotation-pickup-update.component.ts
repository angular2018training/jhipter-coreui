
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { QuotationPickupService } from '../../shared/service/quotation-pickup.service';

import { QuotationPickup } from '../../shared/model/quotation-pickup.model';
            import { Company } from '../../shared/model/company.model';
            import { CompanyService} from '../../shared/service/company.service';
            import { Province } from '../../shared/model/province.model';
            import { ProvinceService} from '../../shared/service/province.service';
            import { DistrictType } from '../../shared/model/district-type.model';
            import { DistrictTypeService} from '../../shared/service/district-type.service';
            import { Quotation } from '../../shared/model/quotation.model';
            import { QuotationService} from '../../shared/service/quotation.service';

@Component({
    selector: 'jhi-quotation-pickup-update',
    templateUrl: './quotation-pickup-update.component.html'
})
export class QuotationPickupUpdateComponent implements OnInit {

    private _quotationPickup: QuotationPickup;
    isSaving: boolean;
    private currentAccount : any;

    provinces: Province[];

    districttypes: DistrictType[];

    quotations: Quotation[];

    constructor(
        private alertService: AlertService,
        private quotationPickupService: QuotationPickupService,
        private principal : Principal,
        private companyService: CompanyService,
        private provinceService: ProvinceService,
        private districtTypeService: DistrictTypeService,
        private quotationService: QuotationService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({quotationPickup}) => {
            this.quotationPickup = quotationPickup;
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.provinceService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<Province[]>) => { this.provinces = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
        if (this.quotationPickup.id !== undefined) {
            this.subscribeToSaveResponse(
                this.quotationPickupService.update(this.quotationPickup));
        } else {
            this.quotationPickup.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.quotationPickupService.create(this.quotationPickup));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<QuotationPickup>>) {
        result.subscribe((res: HttpResponse<QuotationPickup>) =>
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

    trackProvinceById(index: number, item: Province) {
        return item.id;
    }

    trackDistrictTypeById(index: number, item: DistrictType) {
        return item.id;
    }

    trackQuotationById(index: number, item: Quotation) {
        return item.id;
    }
    get quotationPickup() {
        return this._quotationPickup;
    }

    set quotationPickup(quotationPickup: QuotationPickup) {
        this._quotationPickup = quotationPickup;
    }
}
