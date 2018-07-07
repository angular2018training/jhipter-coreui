import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationCod } from '../../shared/model/quotation-cod.model';
import { QuotationCodDetailPopupService } from './quotation-cod-detail-popup.service';
import { QuotationCodService } from '../../shared/service/quotation-cod.service';

@Component({
    selector: 'jhi-quotation-cod-detail-delete-dialog',
    templateUrl: './quotation-cod-detail-delete-dialog.component.html'
})
export class QuotationCodDetailDeleteDialogComponent {

    quotationCod: QuotationCod;

    constructor(
        private quotationCodService: QuotationCodService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.quotationCodService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'quotation-cod-detail-delete-item',
                content: 'Deleted an quotationCod'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quotation-cod-delete-popup',
    template: ''
})
export class QuotationCodDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private quotationCodDetailPopupService: QuotationCodDetailPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.quotationCodDetailPopupService
                .open(QuotationCodDetailDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
