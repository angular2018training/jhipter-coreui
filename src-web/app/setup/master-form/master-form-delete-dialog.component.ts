import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { MasterForm } from './master-form.model';
import { MasterFormPopupService } from './master-form-popup.service';
import { MasterFormService } from './master-form.service';

@Component({
    selector: 'jhi-master-form-delete-dialog',
    templateUrl: './master-form-delete-dialog.component.html'
})
export class MasterFormDeleteDialogComponent {

    masterForm: MasterForm;

    constructor(
        private masterFormService: MasterFormService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.masterFormService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'masterFormListModification',
                content: 'Deleted an masterForm'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-master-form-delete-popup',
    template: ''
})
export class MasterFormDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private masterFormPopupService: MasterFormPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.masterFormPopupService
                .open(MasterFormDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
