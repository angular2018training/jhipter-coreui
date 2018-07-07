import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationDomesticDelivery } from '../../shared/model/quotation-domestic-delivery.model';
import { QuotationDomesticDeliveryDetailPopupService } from './quotation-domestic-delivery-detail-popup.service';
import { QuotationDomesticDeliveryService } from '../../shared/service/quotation-domestic-delivery.service';

@Component({
    selector: 'jhi-quotation-domestic-delivery-detail-delete-dialog',
    templateUrl: './quotation-domestic-delivery-detail-delete-dialog.component.html'
})
export class QuotationDomesticDeliveryDetailDeleteDialogComponent {

    quotationDomesticDelivery: QuotationDomesticDelivery;

    constructor(
        private quotationDomesticDeliveryService: QuotationDomesticDeliveryService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.quotationDomesticDeliveryService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'quotation-domestic-delivery-detail-delete-item',
                content: 'Deleted an quotationDomesticDelivery'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quotation-domestic-delivery-delete-popup',
    template: ''
})
export class QuotationDomesticDeliveryDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private quotationDomesticDeliveryDetailPopupService: QuotationDomesticDeliveryDetailPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.quotationDomesticDeliveryDetailPopupService
                .open(QuotationDomesticDeliveryDetailDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
