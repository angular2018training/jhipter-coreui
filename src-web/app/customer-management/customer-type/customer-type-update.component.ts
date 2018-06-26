import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { CustomerTypeService } from './customer-type.service';

import { CustomerType } from './customer-type.model';
            import { Company, CompanyService } from '../../setup/company';

@Component({
    selector: 'jhi-customer-type-update',
    templateUrl: './customer-type-update.component.html'
})
export class CustomerTypeUpdateComponent implements OnInit {

    private _customerType: CustomerType;
    isSaving: boolean;

    companies: Company[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private customerTypeService: CustomerTypeService,
        private companyService: CompanyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({customerType}) => {
            this.customerType = customerType;
        });
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.customerType.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerTypeService.update(this.customerType));
        } else {
            this.subscribeToSaveResponse(
                this.customerTypeService.create(this.customerType));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CustomerType>>) {
        result.subscribe((res: HttpResponse<CustomerType>) =>
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
    get customerType() {
        return this._customerType;
    }

    set customerType(customerType: CustomerType) {
        this._customerType = customerType;
    }
}
