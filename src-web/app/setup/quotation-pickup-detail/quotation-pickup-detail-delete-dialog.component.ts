import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationPickup } from '../../shared/model/quotation-pickup.model';
import { QuotationPickupDetailPopupService } from './quotation-pickup-detail-popup.service';
import { QuotationPickupService } from '../../shared/service/quotation-pickup.service';

@Component({
    selector: 'jhi-quotation-pickup-detail-delete-dialog',
    templateUrl: './quotation-pickup-detail-delete-dialog.component.html'
})
export class QuotationPickupDetailDeleteDialogComponent {

    quotationPickup: QuotationPickup;

    constructor(
        private quotationPickupService: QuotationPickupService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.quotationPickupService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'quotation-pickup-detail-delete-item',
                content: 'Deleted an quotationPickup'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quotation-pickup-delete-popup',
    template: ''
})
export class QuotationPickupDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private quotationPickupDetailPopupService: QuotationPickupDetailPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.quotationPickupDetailPopupService
                .open(QuotationPickupDetailDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
