
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from '../../shared/constants/input.constants';
import { JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { QuotationItemService } from '../../shared/service/quotation-item.service';

import { QuotationItem } from '../../shared/model/quotation-item.model';
import { Quotation } from '../../shared/model/quotation.model';
import { QuotationService } from '../../shared/service/quotation.service';
import { CompanyService } from '../../shared/service/company.service';
import { Company } from '../../shared/model/company.model';

@Component({
    selector: 'jhi-quotation-item-update',
    templateUrl: './quotation-item-update.component.html'
})
export class QuotationItemUpdateComponent implements OnInit {

    private _quotationItem: QuotationItem;
    isSaving: boolean;
    private currentAccount : any;

    quotations: Quotation[];
        createDate: string;

    constructor(
        private dataUtils: JhiDataUtils,
        private alertService: AlertService,
        private quotationItemService: QuotationItemService,
        private principal : Principal,
        private quotationService: QuotationService,
        private companyService: CompanyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({quotationItem}) => {
            this.quotationItem = quotationItem;
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
            this.quotationService.query({"companyId.equals":this.currentAccount.companyId,"pageSize":ITEMS_QUERY_ALL})
            .subscribe((res: HttpResponse<Quotation[]>) => { this.quotations = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        });

    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
                this.quotationItem.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        if (this.quotationItem.id !== undefined) {
            this.subscribeToSaveResponse(
                this.quotationItemService.update(this.quotationItem));
        } else {
            this.quotationItem.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.quotationItemService.create(this.quotationItem));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<QuotationItem>>) {
        result.subscribe((res: HttpResponse<QuotationItem>) =>
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

    trackQuotationById(index: number, item: Quotation) {
        return item.id;
    }

    trackCompanyById(index: number, item: Company) {
        return item.id;
    }
    get quotationItem() {
        return this._quotationItem;
    }

    set quotationItem(quotationItem: QuotationItem) {
        this._quotationItem = quotationItem;
        this.createDate = moment(quotationItem.createDate).format(DATE_TIME_FORMAT);
    }
}
