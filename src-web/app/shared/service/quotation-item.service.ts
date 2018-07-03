import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { QuotationItem } from '../model/quotation-item.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<QuotationItem>;

@Injectable({ providedIn: 'root' })
export class QuotationItemService {

    private resourceUrl =  SERVER_API_URL + 'api/quotation-items';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/quotation-items';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/quotation-items';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(quotationItem: QuotationItem): Observable<EntityResponseType> {
        const copy = this.convert(quotationItem);
        return this.http.post<QuotationItem>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(quotationItem: QuotationItem): Observable<EntityResponseType> {
        const copy = this.convert(quotationItem);
        return this.http.put<QuotationItem>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<QuotationItem>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<QuotationItem[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationItem[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationItem[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<QuotationItem[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationItem[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationItem[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<QuotationItem[]>> {
            const options = createRequestOption(req);
        return this.http.get<QuotationItem[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationItem[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: QuotationItem = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<QuotationItem[]>): HttpResponse<QuotationItem[]> {
        const jsonResponse: QuotationItem[] = res.body;
        const body: QuotationItem[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to QuotationItem.
     */
    private convertItemFromServer(quotationItem: QuotationItem): QuotationItem {
        const copy: QuotationItem = Object.assign({}, quotationItem);
        copy.createDate = this.dateUtils
            .convertDateTimeFromServer(quotationItem.createDate);
        return copy;
    }

    /**
     * Convert a QuotationItem to a JSON which can be sent to the server.
     */
    private convert(quotationItem: QuotationItem): QuotationItem {
        const copy: QuotationItem = Object.assign({}, quotationItem);

        copy.createDate = quotationItem.createDate != null && quotationItem.createDate.isValid() ? quotationItem.createDate.toJSON() : null;
        return copy;
    }
}
