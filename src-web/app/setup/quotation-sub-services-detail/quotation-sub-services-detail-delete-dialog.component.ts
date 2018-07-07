import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationSubServices } from '../../shared/model/quotation-sub-services.model';
import { QuotationSubServicesDetailPopupService } from './quotation-sub-services-detail-popup.service';
import { QuotationSubServicesService } from '../../shared/service/quotation-sub-services.service';

@Component({
    selector: 'jhi-quotation-sub-services-detail-delete-dialog',
    templateUrl: './quotation-sub-services-detail-delete-dialog.component.html'
})
export class QuotationSubServicesDetailDeleteDialogComponent {

    quotationSubServices: QuotationSubServices;

    constructor(
        private quotationSubServicesService: QuotationSubServicesService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.quotationSubServicesService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'quotation-sub-services-detail-delete-item',
                content: 'Deleted an quotationSubServices'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quotation-sub-services-delete-popup',
    template: ''
})
export class QuotationSubServicesDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private quotationSubServicesDetailPopupService: QuotationSubServicesDetailPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.quotationSubServicesDetailPopupService
                .open(QuotationSubServicesDetailDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
