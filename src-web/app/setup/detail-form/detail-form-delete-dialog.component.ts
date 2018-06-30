import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { DetailForm } from './detail-form.model';
import { DetailFormPopupService } from './detail-form-popup.service';
import { DetailFormService } from './detail-form.service';

@Component({
    selector: 'jhi-detail-form-delete-dialog',
    templateUrl: './detail-form-delete-dialog.component.html'
})
export class DetailFormDeleteDialogComponent {

    detailForm: DetailForm;

    constructor(
        private detailFormService: DetailFormService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.detailFormService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'detailFormListModification',
                content: 'Deleted an detailForm'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-detail-form-delete-popup',
    template: ''
})
export class DetailFormDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private detailFormPopupService: DetailFormPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.detailFormPopupService
                .open(DetailFormDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
