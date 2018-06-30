import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { MasterForm } from './master-form.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<MasterForm>;

@Injectable({ providedIn: 'root' })
export class MasterFormService {

    private resourceUrl =  SERVER_API_URL + 'api/master-forms';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/master-forms';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/master-forms';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(masterForm: MasterForm): Observable<EntityResponseType> {
        const copy = this.convert(masterForm);
        return this.http.post<MasterForm>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(masterForm: MasterForm): Observable<EntityResponseType> {
        const copy = this.convert(masterForm);
        return this.http.put<MasterForm>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<MasterForm>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<MasterForm[]>> {
        const options = createRequestOption(req);
        return this.http.get<MasterForm[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<MasterForm[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<MasterForm[]>> {
        const options = createRequestOption(req);
        return this.http.get<MasterForm[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<MasterForm[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<MasterForm[]>> {
            const options = createRequestOption(req);
        return this.http.get<MasterForm[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<MasterForm[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: MasterForm = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<MasterForm[]>): HttpResponse<MasterForm[]> {
        const jsonResponse: MasterForm[] = res.body;
        const body: MasterForm[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to MasterForm.
     */
    private convertItemFromServer(masterForm: MasterForm): MasterForm {
        const copy: MasterForm = Object.assign({}, masterForm);
        copy.receiveTime = this.dateUtils
            .convertDateTimeFromServer(masterForm.receiveTime);
        return copy;
    }

    /**
     * Convert a MasterForm to a JSON which can be sent to the server.
     */
    private convert(masterForm: MasterForm): MasterForm {
        const copy: MasterForm = Object.assign({}, masterForm);

        copy.receiveTime = masterForm.receiveTime != null && masterForm.receiveTime.isValid() ? masterForm.receiveTime.toJSON() : null;
        return copy;
    }
}
