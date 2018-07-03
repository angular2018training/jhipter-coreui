import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { Quotation } from '../../shared/model/quotation.model';
import { QuotationPopupService } from './quotation-popup.service';
import { QuotationService } from '../../shared/service/quotation.service';

@Component({
    selector: 'jhi-quotation-delete-dialog',
    templateUrl: './quotation-delete-dialog.component.html'
})
export class QuotationDeleteDialogComponent {

    quotation: Quotation;

    constructor(
        private quotationService: QuotationService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.quotationService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'quotationListModification',
                content: 'Deleted an quotation'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quotation-delete-popup',
    template: ''
})
export class QuotationDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private quotationPopupService: QuotationPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.quotationPopupService
                .open(QuotationDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
