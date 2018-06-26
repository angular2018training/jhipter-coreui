import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UserPosition } from './user-position.model';
import { UserPositionPopupService } from './user-position-popup.service';
import { UserPositionService } from './user-position.service';

@Component({
    selector: 'jhi-user-position-delete-dialog',
    templateUrl: './user-position-delete-dialog.component.html'
})
export class UserPositionDeleteDialogComponent {

    userPosition: UserPosition;

    constructor(
        private userPositionService: UserPositionService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.userPositionService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'userPositionListModification',
                content: 'Deleted an userPosition'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-user-position-delete-popup',
    template: ''
})
export class UserPositionDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userPositionPopupService: UserPositionPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.userPositionPopupService
                .open(UserPositionDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
