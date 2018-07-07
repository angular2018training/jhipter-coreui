import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationReturn } from '../../shared/model/quotation-return.model';
import { QuotationReturnPopupService } from './quotation-return-popup.service';
import { QuotationReturnService } from '../../shared/service/quotation-return.service';

@Component({
    selector: 'jhi-quotation-return-delete-dialog',
    templateUrl: './quotation-return-delete-dialog.component.html'
})
export class QuotationReturnDeleteDialogComponent {

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
                name: 'quotationReturnListModification',
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
        private quotationReturnPopupService: QuotationReturnPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.quotationReturnPopupService
                .open(QuotationReturnDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
