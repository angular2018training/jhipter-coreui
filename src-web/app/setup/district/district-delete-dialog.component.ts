import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { District } from './district.model';
import { DistrictPopupService } from './district-popup.service';
import { DistrictService } from './district.service';

@Component({
    selector: 'jhi-district-delete-dialog',
    templateUrl: './district-delete-dialog.component.html'
})
export class DistrictDeleteDialogComponent {

    district: District;

    constructor(
        private districtService: DistrictService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.districtService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'districtListModification',
                content: 'Deleted an district'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-district-delete-popup',
    template: ''
})
export class DistrictDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private districtPopupService: DistrictPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.districtPopupService
                .open(DistrictDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
