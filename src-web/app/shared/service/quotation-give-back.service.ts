import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { QuotationGiveBack } from '../model/quotation-give-back.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<QuotationGiveBack>;

@Injectable({ providedIn: 'root' })
export class QuotationGiveBackService {

    private resourceUrl =  SERVER_API_URL + 'api/quotation-give-backs';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/quotation-give-backs';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/quotation-give-backs';

    constructor(private http: HttpClient) { }

    create(quotationGiveBack: QuotationGiveBack): Observable<EntityResponseType> {
        const copy = this.convert(quotationGiveBack);
        return this.http.post<QuotationGiveBack>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(quotationGiveBack: QuotationGiveBack): Observable<EntityResponseType> {
        const copy = this.convert(quotationGiveBack);
        return this.http.put<QuotationGiveBack>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<QuotationGiveBack>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<QuotationGiveBack[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationGiveBack[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationGiveBack[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<QuotationGiveBack[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationGiveBack[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationGiveBack[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<QuotationGiveBack[]>> {
            const options = createRequestOption(req);
        return this.http.get<QuotationGiveBack[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationGiveBack[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: QuotationGiveBack = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<QuotationGiveBack[]>): HttpResponse<QuotationGiveBack[]> {
        const jsonResponse: QuotationGiveBack[] = res.body;
        const body: QuotationGiveBack[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to QuotationGiveBack.
     */
    private convertItemFromServer(quotationGiveBack: QuotationGiveBack): QuotationGiveBack {
        const copy: QuotationGiveBack = Object.assign({}, quotationGiveBack);
        return copy;
    }

    /**
     * Convert a QuotationGiveBack to a JSON which can be sent to the server.
     */
    private convert(quotationGiveBack: QuotationGiveBack): QuotationGiveBack {
        const copy: QuotationGiveBack = Object.assign({}, quotationGiveBack);
        return copy;
    }
}
