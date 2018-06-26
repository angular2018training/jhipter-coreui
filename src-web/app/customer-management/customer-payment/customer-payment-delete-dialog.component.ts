import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CustomerPayment } from './customer-payment.model';
import { CustomerPaymentPopupService } from './customer-payment-popup.service';
import { CustomerPaymentService } from './customer-payment.service';

@Component({
    selector: 'jhi-customer-payment-delete-dialog',
    templateUrl: './customer-payment-delete-dialog.component.html'
})
export class CustomerPaymentDeleteDialogComponent {

    customerPayment: CustomerPayment;

    constructor(
        private customerPaymentService: CustomerPaymentService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.customerPaymentService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'customerPaymentListModification',
                content: 'Deleted an customerPayment'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-customer-payment-delete-popup',
    template: ''
})
export class CustomerPaymentDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerPaymentPopupService: CustomerPaymentPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.customerPaymentPopupService
                .open(CustomerPaymentDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
