
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';

import { QuotationTypeService } from '../../shared/service/quotation-type.service';

import { QuotationType } from '../../shared/model/quotation-type.model';

@Component({
    selector: 'jhi-quotation-type-update',
    templateUrl: './quotation-type-update.component.html'
})
export class QuotationTypeUpdateComponent implements OnInit {

    private _quotationType: QuotationType;
    isSaving: boolean;
    private currentAccount : any;

    constructor(
        private quotationTypeService: QuotationTypeService,
        private principal : Principal,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({quotationType}) => {
            this.quotationType = quotationType;
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
        if (this.quotationType.id !== undefined) {
            this.subscribeToSaveResponse(
                this.quotationTypeService.update(this.quotationType));
        } else {
            this.subscribeToSaveResponse(
                this.quotationTypeService.create(this.quotationType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<QuotationType>>) {
        result.subscribe((res: HttpResponse<QuotationType>) =>
            this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get quotationType() {
        return this._quotationType;
    }

    set quotationType(quotationType: QuotationType) {
        this._quotationType = quotationType;
    }
}
