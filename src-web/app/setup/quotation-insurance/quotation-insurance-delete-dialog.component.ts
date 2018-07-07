import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationInsurance } from '../../shared/model/quotation-insurance.model';
import { QuotationInsurancePopupService } from './quotation-insurance-popup.service';
import { QuotationInsuranceService } from '../../shared/service/quotation-insurance.service';

@Component({
    selector: 'jhi-quotation-insurance-delete-dialog',
    templateUrl: './quotation-insurance-delete-dialog.component.html'
})
export class QuotationInsuranceDeleteDialogComponent {

    quotationInsurance: QuotationInsurance;

    constructor(
        private quotationInsuranceService: QuotationInsuranceService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.quotationInsuranceService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'quotationInsuranceListModification',
                content: 'Deleted an quotationInsurance'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quotation-insurance-delete-popup',
    template: ''
})
export class QuotationInsuranceDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private quotationInsurancePopupService: QuotationInsurancePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.quotationInsurancePopupService
                .open(QuotationInsuranceDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
