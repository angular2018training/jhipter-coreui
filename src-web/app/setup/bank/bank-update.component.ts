
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { BankService } from './bank.service';

import { Bank } from './bank.model';
            import { Company, CompanyService } from '../company';

@Component({
    selector: 'jhi-bank-update',
    templateUrl: './bank-update.component.html'
})
export class BankUpdateComponent implements OnInit {

    private _bank: Bank;
    isSaving: boolean;
    private currentAccount : any;

    constructor(
        private alertService: AlertService,
        private bankService: BankService,
        private principal : Principal,
        private companyService: CompanyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({bank}) => {
            this.bank = bank;
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
        if (this.bank.id !== undefined) {
            this.subscribeToSaveResponse(
                this.bankService.update(this.bank));
        } else {
            this.bank.companyId = this.currentAccount.companyId;
            this.subscribeToSaveResponse(
                this.bankService.create(this.bank));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Bank>>) {
        result.subscribe((res: HttpResponse<Bank>) =>
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
    get bank() {
        return this._bank;
    }

    set bank(bank: Bank) {
        this._bank = bank;
    }
}
