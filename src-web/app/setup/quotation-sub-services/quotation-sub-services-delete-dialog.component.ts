import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationSubServices } from '../../shared/model/quotation-sub-services.model';
import { QuotationSubServicesPopupService } from './quotation-sub-services-popup.service';
import { QuotationSubServicesService } from '../../shared/service/quotation-sub-services.service';

@Component({
    selector: 'jhi-quotation-sub-services-delete-dialog',
    templateUrl: './quotation-sub-services-delete-dialog.component.html'
})
export class QuotationSubServicesDeleteDialogComponent {

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
                name: 'quotationSubServicesListModification',
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
        private quotationSubServicesPopupService: QuotationSubServicesPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.quotationSubServicesPopupService
                .open(QuotationSubServicesDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
