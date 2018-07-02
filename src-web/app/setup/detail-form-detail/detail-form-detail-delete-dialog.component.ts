import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { DetailForm } from '../detail-form/detail-form.model';
import { DetailFormDetailPopupService } from './detail-form-detail-popup.service';
import { DetailFormService } from '../detail-form/detail-form.service';

@Component({
    selector: 'jhi-detail-form-detail-delete-dialog',
    templateUrl: './detail-form-detail-delete-dialog.component.html'
})
export class DetailFormDetailDeleteDialogComponent {

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
                name: 'detail-form-detail-delete-item',
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
        private detailFormDetailPopupService: DetailFormDetailPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.detailFormDetailPopupService
                .open(DetailFormDetailDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
