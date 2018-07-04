import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { UserPostOffice } from '../../shared/model/user-post-office.model';
import { UserPostOfficePopupService } from './user-post-office-popup.service';
import { UserPostOfficeService } from '../../shared/service/user-post-office.service';

@Component({
    selector: 'jhi-user-post-office-delete-dialog',
    templateUrl: './user-post-office-delete-dialog.component.html'
})
export class UserPostOfficeDeleteDialogComponent {

    userPostOffice: UserPostOffice;

    constructor(
        private userPostOfficeService: UserPostOfficeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.userPostOfficeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'userPostOfficeListModification',
                content: 'Deleted an userPostOffice'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-user-post-office-delete-popup',
    template: ''
})
export class UserPostOfficeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private userPostOfficePopupService: UserPostOfficePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.userPostOfficePopupService
                .open(UserPostOfficeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
