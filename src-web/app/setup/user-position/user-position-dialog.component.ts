import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs/Observable';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { UserPosition } from './user-position.model';
import { UserPositionPopupService } from './user-position-popup.service';
import { UserPositionService } from './user-position.service';
import { PostOffice, PostOfficeService } from '../post-office';
import { Position, PositionService } from '../position';
import { UserExtraInfo, UserExtraInfoService } from '../user-extra-info';

@Component({
    selector: 'jhi-user-position-dialog',
    templateUrl: './user-position-dialog.component.html'
})
export class UserPositionDialogComponent implements OnInit {

    userPosition: UserPosition;
    isSaving: boolean;

    postoffices: PostOffice[];

    positions: Position[];

    userextrainfos: UserExtraInfo[];

    constructor(
        public activeModal: NgbActiveModal,
        private jhiAlertService: JhiAlertService,
        private userPositionService: UserPositionService,
        private postOfficeService: PostOfficeService,
        private positionService: PositionService,
        private userExtraInfoService: UserExtraInfoService,
        private eventManager: JhiEventManager
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.postOfficeService.query()
            .subscribe((res: HttpResponse<PostOffice[]>) => { this.postoffices = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.positionService.query()
            .subscribe((res: HttpResponse<Position[]>) => { this.positions = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
        this.userExtraInfoService.query()
            .subscribe((res: HttpResponse<UserExtraInfo[]>) => { this.userextrainfos = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    save() {
        this.isSaving = true;
        if (this.userPosition.id !== undefined) {
            this.subscribeToSaveResponse(
                this.userPositionService.update(this.userPosition));
        } else {
            this.subscribeToSaveResponse(
                this.userPositionService.create(this.userPosition));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<UserPosition>>) {
        result.subscribe((res: HttpResponse<UserPosition>) =>
            this.onSaveSuccess(res.body), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess(result: UserPosition) {
        this.eventManager.broadcast({ name: 'userPositionListModification', content: 'OK'});
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

    trackPositionById(index: number, item: Position) {
        return item.id;
    }

    trackUserExtraInfoById(index: number, item: UserExtraInfo) {
        return item.id;
    }
}

@Component({
    selector: 'jhi-user-position-popup',
    template: ''
})
export class UserPositionPopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userPositionPopupService: UserPositionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            if ( params['id'] ) {
                this.userPositionPopupService
                    .open(UserPositionDialogComponent as Component, params['id']);
            } else {
                this.userPositionPopupService
                    .open(UserPositionDialogComponent as Component);
            }
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
