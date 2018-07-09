import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { DistrictType } from './district-type.model';
import { DistrictTypePopupService } from './district-type-popup.service';
import { DistrictTypeService } from './district-type.service';

@Component({
    selector: 'jhi-district-type-delete-dialog',
    templateUrl: './district-type-delete-dialog.component.html'
})
export class DistrictTypeDeleteDialogComponent {

    districtType: DistrictType;

    constructor(
        private districtTypeService: DistrictTypeService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.districtTypeService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'districtTypeListModification',
                content: 'Deleted an districtType'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-district-type-delete-popup',
    template: ''
})
export class DistrictTypeDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private districtTypePopupService: DistrictTypePopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.districtTypePopupService
                .open(DistrictTypeDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
