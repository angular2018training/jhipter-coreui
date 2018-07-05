
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from '../../shared/constants/input.constants';
import { JhiAlertService,  } from 'ng-jhipster';

import { QuotationService } from '../../shared/service/quotation.service';

import { Quotation } from '../../shared/model/quotation.model';
            import { Company } from '../../shared/model/company.model';
            import { CompanyService} from '../../shared/service/company.service';

@Component({
    selector: 'jhi-quotation-update',
    templateUrl: './quotation-update.component.html'
})
export class QuotationUpdateComponent implements OnInit {

    private _quotation: Quotation;
    isSaving: boolean;
    private currentAccount : any;
        createDate: string;
        activeFromDp: any;

    constructor(
        private alertService: AlertService,
        private quotationService: QuotationService,
        private principal : Principal,
        private companyService: CompanyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({quotation}) => {
            this.quotation = quotation;
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });

    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
                this.quotation.createDate = moment(this.createDate, DATE_TIME_FORMAT);
        if (this.quotation.id !== undefined) {
            this.subscribeToSaveResponse(
                this.quotationService.update(this.quotation));
        } else {
            this.quotation.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.quotationService.create(this.quotation));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Quotation>>) {
        result.subscribe((res: HttpResponse<Quotation>) =>
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
    get quotation() {
        return this._quotation;
    }

    set quotation(quotation: Quotation) {
        this._quotation = quotation;
        this.createDate = moment(quotation.createDate).format(DATE_TIME_FORMAT);
    }
}
