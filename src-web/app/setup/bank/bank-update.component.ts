import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
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

    companies: Company[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private bankService: BankService,
        private companyService: CompanyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({bank}) => {
            this.bank = bank;
        });
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
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
        this.jhiAlertService.error(errorMessage, null, null);
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
