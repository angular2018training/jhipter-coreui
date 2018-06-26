import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { CustomerSourceService } from './customer-source.service';

import { CustomerSource } from './customer-source.model';
            import { Company, CompanyService } from '../../setup/company';

@Component({
    selector: 'jhi-customer-source-update',
    templateUrl: './customer-source-update.component.html'
})
export class CustomerSourceUpdateComponent implements OnInit {

    private _customerSource: CustomerSource;
    isSaving: boolean;

    companies: Company[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private customerSourceService: CustomerSourceService,
        private companyService: CompanyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({customerSource}) => {
            this.customerSource = customerSource;
        });
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.customerSource.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerSourceService.update(this.customerSource));
        } else {
            this.subscribeToSaveResponse(
                this.customerSourceService.create(this.customerSource));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CustomerSource>>) {
        result.subscribe((res: HttpResponse<CustomerSource>) =>
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
    get customerSource() {
        return this._customerSource;
    }

    set customerSource(customerSource: CustomerSource) {
        this._customerSource = customerSource;
    }
}
