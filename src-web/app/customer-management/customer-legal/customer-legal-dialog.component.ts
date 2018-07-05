import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { CustomerLegal } from './customer-legal.model';
import { CustomerLegalPopupService } from './customer-legal-popup.service';
import { CustomerLegalService } from './customer-legal.service';
import { FileUpload } from '../../shared/model/file-upload.model';
import { FileUploadService } from '../../setup/file-upload';

@Component({
    selector: 'jhi-customer-legal-dialog',
    templateUrl: './customer-legal-dialog.component.html'
})
export class CustomerLegalDialogComponent implements OnInit {

    customerLegal: CustomerLegal;
    isSaving: boolean;

    fileuploads: FileUpload[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private customerLegalService: CustomerLegalService,
        private fileUploadService: FileUploadService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.fileUploadService.query()
            .subscribe((res: HttpResponse<FileUpload[]>) => { this.fileuploads = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.customerLegal.id !== undefined) {
            this.subscribeToSaveResponse(
                this.customerLegalService.update(this.customerLegal));
        } else {
            this.subscribeToSaveResponse(
                this.customerLegalService.create(this.customerLegal));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<CustomerLegal>>) {
        result.subscribe((res: HttpResponse<CustomerLegal>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: CustomerLegal) {
        this.eventManager.broadcast({ name: 'customerLegalListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackFileUploadById(index: number, item: FileUpload) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}

@Component({
    selector: 'jhi-customer-legal-popup',
    template: ''
})
export class CustomerLegalPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerLegalPopupService: CustomerLegalPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.customerLegalPopupService
                    .open(CustomerLegalDialogComponent as Component, params['id']);
            } else {
                this.customerLegalPopupService
                    .open(CustomerLegalDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
