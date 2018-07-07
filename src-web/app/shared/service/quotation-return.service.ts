import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { QuotationReturn } from '../model/quotation-return.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<QuotationReturn>;

@Injectable({ providedIn: 'root' })
export class QuotationReturnService {

    private resourceUrl =  SERVER_API_URL + 'api/quotation-returns';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/quotation-returns';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/quotation-returns';

    constructor(private http: HttpClient) { }

    create(quotationReturn: QuotationReturn): Observable<EntityResponseType> {
        const copy = this.convert(quotationReturn);
        return this.http.post<QuotationReturn>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(quotationReturn: QuotationReturn): Observable<EntityResponseType> {
        const copy = this.convert(quotationReturn);
        return this.http.put<QuotationReturn>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<QuotationReturn>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<QuotationReturn[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationReturn[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationReturn[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<QuotationReturn[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationReturn[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationReturn[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<QuotationReturn[]>> {
            const options = createRequestOption(req);
        return this.http.get<QuotationReturn[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationReturn[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: QuotationReturn = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<QuotationReturn[]>): HttpResponse<QuotationReturn[]> {
        const jsonResponse: QuotationReturn[] = res.body;
        const body: QuotationReturn[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to QuotationReturn.
     */
    private convertItemFromServer(quotationReturn: QuotationReturn): QuotationReturn {
        const copy: QuotationReturn = Object.assign({}, quotationReturn);
        return copy;
    }

    /**
     * Convert a QuotationReturn to a JSON which can be sent to the server.
     */
    private convert(quotationReturn: QuotationReturn): QuotationReturn {
        const copy: QuotationReturn = Object.assign({}, quotationReturn);
        return copy;
    }
}
