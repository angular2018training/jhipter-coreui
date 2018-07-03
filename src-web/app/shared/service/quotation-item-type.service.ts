import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { QuotationItemType } from '../model/quotation-item-type.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<QuotationItemType>;

@Injectable({ providedIn: 'root' })
export class QuotationItemTypeService {

    private resourceUrl =  SERVER_API_URL + 'api/quotation-item-types';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/quotation-item-types';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/quotation-item-types';

    constructor(private http: HttpClient) { }

    create(quotationItemType: QuotationItemType): Observable<EntityResponseType> {
        const copy = this.convert(quotationItemType);
        return this.http.post<QuotationItemType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(quotationItemType: QuotationItemType): Observable<EntityResponseType> {
        const copy = this.convert(quotationItemType);
        return this.http.put<QuotationItemType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<QuotationItemType>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<QuotationItemType[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationItemType[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationItemType[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<QuotationItemType[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationItemType[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationItemType[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<QuotationItemType[]>> {
            const options = createRequestOption(req);
        return this.http.get<QuotationItemType[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationItemType[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: QuotationItemType = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<QuotationItemType[]>): HttpResponse<QuotationItemType[]> {
        const jsonResponse: QuotationItemType[] = res.body;
        const body: QuotationItemType[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to QuotationItemType.
     */
    private convertItemFromServer(quotationItemType: QuotationItemType): QuotationItemType {
        const copy: QuotationItemType = Object.assign({}, quotationItemType);
        return copy;
    }

    /**
     * Convert a QuotationItemType to a JSON which can be sent to the server.
     */
    private convert(quotationItemType: QuotationItemType): QuotationItemType {
        const copy: QuotationItemType = Object.assign({}, quotationItemType);
        return copy;
    }
}
