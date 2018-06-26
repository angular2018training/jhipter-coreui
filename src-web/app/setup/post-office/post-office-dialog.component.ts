import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { PostOffice } from './post-office.model';
import { PostOfficePopupService } from './post-office-popup.service';
import { PostOfficeService } from './post-office.service';
import { Province, ProvinceService } from '../province';

@Component({
    selector: 'jhi-post-office-dialog',
    templateUrl: './post-office-dialog.component.html'
})
export class PostOfficeDialogComponent implements OnInit {

    postOffice: PostOffice;
    isSaving: boolean;

    provinces: Province[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private postOfficeService: PostOfficeService,
        private provinceService: ProvinceService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.provinceService.query()
            .subscribe((res: HttpResponse<Province[]>) => { this.provinces = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.postOffice.id !== undefined) {
            this.subscribeToSaveResponse(
                this.postOfficeService.update(this.postOffice));
        } else {
            this.subscribeToSaveResponse(
                this.postOfficeService.create(this.postOffice));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<PostOffice>>) {
        result.subscribe((res: HttpResponse<PostOffice>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: PostOffice) {
        this.eventManager.broadcast({ name: 'postOfficeListModification', content: 'OK'});
        this.isSaving = false;
        this.activeModal.dismiss(result);
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(error: any) {
        this.jhiAlertService.error(error.message, null, null);
    }

    trackProvinceById(index: number, item: Province) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-post-office-popup',
    template: ''
})
export class PostOfficePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private postOfficePopupService: PostOfficePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.postOfficePopupService
                    .open(PostOfficeDialogComponent as Component, params['id']);
            } else {
                this.postOfficePopupService
                    .open(PostOfficeDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
