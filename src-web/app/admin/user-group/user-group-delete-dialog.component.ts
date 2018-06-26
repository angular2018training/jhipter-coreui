import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UserGroup } from './user-group.model';
import { UserGroupPopupService } from './user-group-popup.service';
import { UserGroupService } from './user-group.service';

@Component({
    selector: 'jhi-user-group-delete-dialog',
    templateUrl: './user-group-delete-dialog.component.html'
})
export class UserGroupDeleteDialogComponent {

    userGroup: UserGroup;

    constructor(
        private userGroupService: UserGroupService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.userGroupService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'userGroupListModification',
                content: 'Deleted an userGroup'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-user-group-delete-popup',
    template: ''
})
export class UserGroupDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userGroupPopupService: UserGroupPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.userGroupPopupService
                .open(UserGroupDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
