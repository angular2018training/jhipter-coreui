import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationCod } from '../../shared/model/quotation-cod.model';
import { QuotationCodPopupService } from './quotation-cod-popup.service';
import { QuotationCodService } from '../../shared/service/quotation-cod.service';

@Component({
    selector: 'jhi-quotation-cod-delete-dialog',
    templateUrl: './quotation-cod-delete-dialog.component.html'
})
export class QuotationCodDeleteDialogComponent {

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
                name: 'quotationCodListModification',
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
        private quotationCodPopupService: QuotationCodPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.quotationCodPopupService
                .open(QuotationCodDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
