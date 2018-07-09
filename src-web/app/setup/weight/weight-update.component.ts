
import { Component, OnInit } from '@angular/core';

import {Principal} from '../../shared/';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import {ITEMS_QUERY_ALL} from '../../shared/';
import {AlertService} from '../../shared/alert/alert-service';
import { Observable } from 'rxjs';

import { WeightService } from './weight.service';

import { Weight } from './weight.model';

@Component({
    selector: 'jhi-weight-update',
    templateUrl: './weight-update.component.html'
})
export class WeightUpdateComponent implements OnInit {

    private _weight: Weight;
    isSaving: boolean;
    private currentAccount : any;

    constructor(
        private weightService: WeightService,
        private principal : Principal,
        private route: ActivatedRoute
    ) {
    }

    ngOnInit() {
        this.isSaving = false;
        this.route.data.subscribe(({weight}) => {
            this.weight = weight;
        });
        this.principal.identity().then((account) => {
            this.currentAccount = account;
        });

    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.weight.id !== undefined) {
            this.subscribeToSaveResponse(
                this.weightService.update(this.weight));
        } else {
            this.subscribeToSaveResponse(
                this.weightService.create(this.weight));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<Weight>>) {
        result.subscribe((res: HttpResponse<Weight>) =>
            this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get weight() {
        return this._weight;
    }

    set weight(weight: Weight) {
        this._weight = weight;
    }
}
