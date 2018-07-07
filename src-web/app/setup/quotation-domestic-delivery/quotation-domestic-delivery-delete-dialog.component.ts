import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationDomesticDelivery } from '../../shared/model/quotation-domestic-delivery.model';
import { QuotationDomesticDeliveryPopupService } from './quotation-domestic-delivery-popup.service';
import { QuotationDomesticDeliveryService } from '../../shared/service/quotation-domestic-delivery.service';

@Component({
    selector: 'jhi-quotation-domestic-delivery-delete-dialog',
    templateUrl: './quotation-domestic-delivery-delete-dialog.component.html'
})
export class QuotationDomesticDeliveryDeleteDialogComponent {

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
                name: 'quotationDomesticDeliveryListModification',
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
        private quotationDomesticDeliveryPopupService: QuotationDomesticDeliveryPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.quotationDomesticDeliveryPopupService
                .open(QuotationDomesticDeliveryDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
