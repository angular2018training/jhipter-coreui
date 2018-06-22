import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Ward } from './ward.model';
import { WardPopupService } from './ward-popup.service';
import { WardService } from './ward.service';

@Component({
    selector: 'jhi-ward-delete-dialog',
    templateUrl: './ward-delete-dialog.component.html'
})
export class WardDeleteDialogComponent {

    ward: Ward;

    constructor(
        private wardService: WardService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.wardService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'wardListModification',
                content: 'Deleted an ward'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ward-delete-popup',
    template: ''
})
export class WardDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private wardPopupService: WardPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.wardPopupService
                .open(WardDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
