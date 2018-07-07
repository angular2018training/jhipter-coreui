import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationReturn } from '../../shared/model/quotation-return.model';
import { QuotationReturnDetailPopupService } from './quotation-return-detail-popup.service';
import { QuotationReturnService } from '../../shared/service/quotation-return.service';

@Component({
    selector: 'jhi-quotation-return-detail-delete-dialog',
    templateUrl: './quotation-return-detail-delete-dialog.component.html'
})
export class QuotationReturnDetailDeleteDialogComponent {

    quotationReturn: QuotationReturn;

    constructor(
        private quotationReturnService: QuotationReturnService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.quotationReturnService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'quotation-return-detail-delete-item',
                content: 'Deleted an quotationReturn'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quotation-return-delete-popup',
    template: ''
})
export class QuotationReturnDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private quotationReturnDetailPopupService: QuotationReturnDetailPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.quotationReturnDetailPopupService
                .open(QuotationReturnDetailDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
