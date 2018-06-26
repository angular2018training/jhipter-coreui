import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CustomerLegal } from './customer-legal.model';
import { CustomerLegalPopupService } from './customer-legal-popup.service';
import { CustomerLegalService } from './customer-legal.service';

@Component({
    selector: 'jhi-customer-legal-delete-dialog',
    templateUrl: './customer-legal-delete-dialog.component.html'
})
export class CustomerLegalDeleteDialogComponent {

    customerLegal: CustomerLegal;

    constructor(
        private customerLegalService: CustomerLegalService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.customerLegalService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'customerLegalListModification',
                content: 'Deleted an customerLegal'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-customer-legal-delete-popup',
    template: ''
})
export class CustomerLegalDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerLegalPopupService: CustomerLegalPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.customerLegalPopupService
                .open(CustomerLegalDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
