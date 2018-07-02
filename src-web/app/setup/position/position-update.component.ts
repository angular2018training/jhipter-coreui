import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService,  } from 'ng-jhipster';

import { PositionService } from './position.service';

import { Position } from './position.model';
            import { Company, CompanyService } from '../company';

@Component({
    selector: 'jhi-position-update',
    templateUrl: './position-update.component.html'
})
export class PositionUpdateComponent implements OnInit {

    private _position: Position;
    isSaving: boolean;

    companies: Company[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private positionService: PositionService,
        private companyService: CompanyService,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({position}) => {
            this.position = position;
        });
        this.companyService.query()
            .subscribe((res: HttpResponse<Company[]>) => { this.companies = res.body; }, (res: HttpErrorResponse) => this.onError(res.message));
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.position.id !== undefined) {
            this.subscribeToSaveResponse(
                this.positionService.update(this.position));
        } else {
            this.subscribeToSaveResponse(
                this.positionService.create(this.position));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Position>>) {
        result.subscribe((res: HttpResponse<Position>) =>
            this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackCompanyById(index: number, item: Company) {
        return item.id;
    }
    get position() {
        return this._position;
    }

    set position(position: Position) {
        this._position = position;
    }
}
