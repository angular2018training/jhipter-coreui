import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CustomerSource } from './customer-source.model';
import { CustomerSourcePopupService } from './customer-source-popup.service';
import { CustomerSourceService } from './customer-source.service';

@Component({
    selector: 'jhi-customer-source-delete-dialog',
    templateUrl: './customer-source-delete-dialog.component.html'
})
export class CustomerSourceDeleteDialogComponent {

    customerSource: CustomerSource;

    constructor(
        private customerSourceService: CustomerSourceService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.customerSourceService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'customerSourceListModification',
                content: 'Deleted an customerSource'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-customer-source-delete-popup',
    template: ''
})
export class CustomerSourceDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private customerSourcePopupService: CustomerSourcePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.customerSourcePopupService
                .open(CustomerSourceDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
