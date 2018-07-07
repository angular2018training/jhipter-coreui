import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationGiveBack } from '../../shared/model/quotation-give-back.model';
import { QuotationGiveBackDetailPopupService } from './quotation-give-back-detail-popup.service';
import { QuotationGiveBackService } from '../../shared/service/quotation-give-back.service';

@Component({
    selector: 'jhi-quotation-give-back-detail-delete-dialog',
    templateUrl: './quotation-give-back-detail-delete-dialog.component.html'
})
export class QuotationGiveBackDetailDeleteDialogComponent {

    quotationGiveBack: QuotationGiveBack;

    constructor(
        private quotationGiveBackService: QuotationGiveBackService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.quotationGiveBackService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'quotation-give-back-detail-delete-item',
                content: 'Deleted an quotationGiveBack'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quotation-give-back-delete-popup',
    template: ''
})
export class QuotationGiveBackDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private quotationGiveBackDetailPopupService: QuotationGiveBackDetailPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.quotationGiveBackDetailPopupService
                .open(QuotationGiveBackDetailDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
