import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { QuotationType } from './quotation-type.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<QuotationType>;

@Injectable({ providedIn: 'root' })
export class QuotationTypeService {

    private resourceUrl =  SERVER_API_URL + 'api/quotation-types';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/quotation-types';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/quotation-types';

    constructor(private http: HttpClient) { }

    create(quotationType: QuotationType): Observable<EntityResponseType> {
        const copy = this.convert(quotationType);
        return this.http.post<QuotationType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(quotationType: QuotationType): Observable<EntityResponseType> {
        const copy = this.convert(quotationType);
        return this.http.put<QuotationType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<QuotationType>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<QuotationType[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationType[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationType[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<QuotationType[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationType[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationType[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<QuotationType[]>> {
            const options = createRequestOption(req);
        return this.http.get<QuotationType[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationType[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: QuotationType = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<QuotationType[]>): HttpResponse<QuotationType[]> {
        const jsonResponse: QuotationType[] = res.body;
        const body: QuotationType[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to QuotationType.
     */
    private convertItemFromServer(quotationType: QuotationType): QuotationType {
        const copy: QuotationType = Object.assign({}, quotationType);
        return copy;
    }

    /**
     * Convert a QuotationType to a JSON which can be sent to the server.
     */
    private convert(quotationType: QuotationType): QuotationType {
        const copy: QuotationType = Object.assign({}, quotationType);
        return copy;
    }
}
