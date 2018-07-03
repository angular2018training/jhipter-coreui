import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationItem } from '../../shared/model/quotation-item.model';
import { QuotationItemPopupService } from './quotation-item-popup.service';
import { QuotationItemService } from '../../shared/service/quotation-item.service';

@Component({
    selector: 'jhi-quotation-item-delete-dialog',
    templateUrl: './quotation-item-delete-dialog.component.html'
})
export class QuotationItemDeleteDialogComponent {

    quotationItem: QuotationItem;

    constructor(
        private quotationItemService: QuotationItemService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.quotationItemService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'quotationItemListModification',
                content: 'Deleted an quotationItem'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quotation-item-delete-popup',
    template: ''
})
export class QuotationItemDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private quotationItemPopupService: QuotationItemPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.quotationItemPopupService
                .open(QuotationItemDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
