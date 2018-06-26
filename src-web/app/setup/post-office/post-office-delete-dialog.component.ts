import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { PostOffice } from './post-office.model';
import { PostOfficePopupService } from './post-office-popup.service';
import { PostOfficeService } from './post-office.service';

@Component({
    selector: 'jhi-post-office-delete-dialog',
    templateUrl: './post-office-delete-dialog.component.html'
})
export class PostOfficeDeleteDialogComponent {

    postOffice: PostOffice;

    constructor(
        private postOfficeService: PostOfficeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.postOfficeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'postOfficeListModification',
                content: 'Deleted an postOffice'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-post-office-delete-popup',
    template: ''
})
export class PostOfficeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private postOfficePopupService: PostOfficePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.postOfficePopupService
                .open(PostOfficeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
