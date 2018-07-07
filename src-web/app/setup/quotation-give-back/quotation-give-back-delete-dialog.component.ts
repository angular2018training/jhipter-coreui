import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { QuotationGiveBack } from '../../shared/model/quotation-give-back.model';
import { QuotationGiveBackPopupService } from './quotation-give-back-popup.service';
import { QuotationGiveBackService } from '../../shared/service/quotation-give-back.service';

@Component({
    selector: 'jhi-quotation-give-back-delete-dialog',
    templateUrl: './quotation-give-back-delete-dialog.component.html'
})
export class QuotationGiveBackDeleteDialogComponent {

    quotationGiveBack: QuotationGiveBack;

    constructor(
        private quotationGiveBackService: QuotationGiveBackService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.quotationGiveBackService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'quotationGiveBackListModification',
                content: 'Deleted an quotationGiveBack'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quotation-give-back-delete-popup',
    template: ''
})
export class QuotationGiveBackDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private quotationGiveBackPopupService: QuotationGiveBackPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.quotationGiveBackPopupService
                .open(QuotationGiveBackDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
