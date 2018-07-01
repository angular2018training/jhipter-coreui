import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import {DetailFormService} from "../detail-form/detail-form.service";
import {DetailForm} from "../detail-form/detail-form.model";
import {DetailFormPopupService} from "../detail-form/detail-form-popup.service";

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
    selector: 'jhi-detail-form-detail-delete-popup',
    template: ''
})
export class DetailFormDetailDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private detailFormPopupService: DetailFormPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.detailFormPopupService
                .open(DetailFormDetailDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
