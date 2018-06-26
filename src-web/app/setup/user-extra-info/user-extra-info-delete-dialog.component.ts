import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UserExtraInfo } from './user-extra-info.model';
import { UserExtraInfoPopupService } from './user-extra-info-popup.service';
import { UserExtraInfoService } from './user-extra-info.service';

@Component({
    selector: 'jhi-user-extra-info-delete-dialog',
    templateUrl: './user-extra-info-delete-dialog.component.html'
})
export class UserExtraInfoDeleteDialogComponent {

    userExtraInfo: UserExtraInfo;

    constructor(
        private userExtraInfoService: UserExtraInfoService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.userExtraInfoService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'userExtraInfoListModification',
                content: 'Deleted an userExtraInfo'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-user-extra-info-delete-popup',
    template: ''
})
export class UserExtraInfoDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userExtraInfoPopupService: UserExtraInfoPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.userExtraInfoPopupService
                .open(UserExtraInfoDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
