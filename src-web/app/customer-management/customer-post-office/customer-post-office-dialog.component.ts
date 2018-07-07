import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';
import { PostOffice } from '../../shared/model/post-office.model';
import { CustomerPostOfficeService } from '../../shared/service/customer-post-office.service';
import { PostOfficeService } from '../../shared/service/post-office.service';
import { CustomerPostOffice } from '../../shared/model/customer-post-office.model';

@Component({
    selector: 'jhi-customer-post-office-dialog',
    templateUrl: './customer-post-office-dialog.component.html'
})
export class CustomerPostOfficeDialogComponent implements OnInit {

    customerPostOffice: CustomerPostOffice;

    isSaving: boolean;

    postoffices: PostOffice[];
    bsConfig = { dateInputFormat: 'DD/MM/YYYY' };

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private customerPostOfficeService: CustomerPostOfficeService,
        private postOfficeService: PostOfficeService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.postOfficeService.query()
            .subscribe((res: HttpResponse<PostOffice[]>) => { this.postoffices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.customerPostOffice.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerPostOfficeService.update(this.customerPostOffice));
        } else {
            this.subscribeToSaveResponse(
                this.customerPostOfficeService.create(this.customerPostOffice));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CustomerPostOffice>>) {
        result.subscribe((res: HttpResponse<CustomerPostOffice>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CustomerPostOffice) {
        this.eventManager.broadcast({ name: 'customerPostOfficeListModification', content: 'OK' });
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackPostOfficeById(index: number, item: PostOffice) {
        return item.id;
    }
}
