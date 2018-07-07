import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { QuotationCod } from '../model/quotation-cod.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<QuotationCod>;

@Injectable({ providedIn: 'root' })
export class QuotationCodService {

    private resourceUrl =  SERVER_API_URL + 'api/quotation-cods';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/quotation-cods';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/quotation-cods';

    constructor(private http: HttpClient) { }

    create(quotationCod: QuotationCod): Observable<EntityResponseType> {
        const copy = this.convert(quotationCod);
        return this.http.post<QuotationCod>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(quotationCod: QuotationCod): Observable<EntityResponseType> {
        const copy = this.convert(quotationCod);
        return this.http.put<QuotationCod>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<QuotationCod>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<QuotationCod[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationCod[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationCod[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<QuotationCod[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationCod[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationCod[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<QuotationCod[]>> {
            const options = createRequestOption(req);
        return this.http.get<QuotationCod[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationCod[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: QuotationCod = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<QuotationCod[]>): HttpResponse<QuotationCod[]> {
        const jsonResponse: QuotationCod[] = res.body;
        const body: QuotationCod[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to QuotationCod.
     */
    private convertItemFromServer(quotationCod: QuotationCod): QuotationCod {
        const copy: QuotationCod = Object.assign({}, quotationCod);
        return copy;
    }

    /**
     * Convert a QuotationCod to a JSON which can be sent to the server.
     */
    private convert(quotationCod: QuotationCod): QuotationCod {
        const copy: QuotationCod = Object.assign({}, quotationCod);
        return copy;
    }
}
