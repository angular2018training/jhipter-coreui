import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { DetailForm } from './detail-form.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<DetailForm>;

@Injectable({ providedIn: 'root' })
export class DetailFormService {

    private resourceUrl =  SERVER_API_URL + 'api/detail-forms';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/detail-forms';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/detail-forms';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(detailForm: DetailForm): Observable<EntityResponseType> {
        const copy = this.convert(detailForm);
        return this.http.post<DetailForm>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(detailForm: DetailForm): Observable<EntityResponseType> {
        const copy = this.convert(detailForm);
        return this.http.put<DetailForm>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<DetailForm>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<DetailForm[]>> {
        const options = createRequestOption(req);
        return this.http.get<DetailForm[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<DetailForm[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<DetailForm[]>> {
        const options = createRequestOption(req);
        return this.http.get<DetailForm[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<DetailForm[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<DetailForm[]>> {
            const options = createRequestOption(req);
        return this.http.get<DetailForm[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<DetailForm[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: DetailForm = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<DetailForm[]>): HttpResponse<DetailForm[]> {
        const jsonResponse: DetailForm[] = res.body;
        const body: DetailForm[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to DetailForm.
     */
    private convertItemFromServer(detailForm: DetailForm): DetailForm {
        const copy: DetailForm = Object.assign({}, detailForm);
        copy.createDate = this.dateUtils
            .convertDateTimeFromServer(detailForm.createDate);
        return copy;
    }

    /**
     * Convert a DetailForm to a JSON which can be sent to the server.
     */
    private convert(detailForm: DetailForm): DetailForm {
        const copy: DetailForm = Object.assign({}, detailForm);

        copy.createDate = detailForm.createDate != null && detailForm.createDate.isValid() ? detailForm.createDate.toJSON() : null;
        return copy;
    }
}
