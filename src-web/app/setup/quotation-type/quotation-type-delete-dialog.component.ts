import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationType } from '../../shared/model/quotation-type.model';
import { QuotationTypePopupService } from './quotation-type-popup.service';
import { QuotationTypeService } from '../../shared/service/quotation-type.service';

@Component({
    selector: 'jhi-quotation-type-delete-dialog',
    templateUrl: './quotation-type-delete-dialog.component.html'
})
export class QuotationTypeDeleteDialogComponent {

    quotationType: QuotationType;

    constructor(
        private quotationTypeService: QuotationTypeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.quotationTypeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'quotationTypeListModification',
                content: 'Deleted an quotationType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quotation-type-delete-popup',
    template: ''
})
export class QuotationTypeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private quotationTypePopupService: QuotationTypePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.quotationTypePopupService
                .open(QuotationTypeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
