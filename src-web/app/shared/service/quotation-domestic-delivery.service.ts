import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { QuotationDomesticDelivery } from '../model/quotation-domestic-delivery.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<QuotationDomesticDelivery>;

@Injectable({ providedIn: 'root' })
export class QuotationDomesticDeliveryService {

    private resourceUrl =  SERVER_API_URL + 'api/quotation-domestic-deliveries';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/quotation-domestic-deliveries';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/quotation-domestic-deliveries';

    constructor(private http: HttpClient) { }

    create(quotationDomesticDelivery: QuotationDomesticDelivery): Observable<EntityResponseType> {
        const copy = this.convert(quotationDomesticDelivery);
        return this.http.post<QuotationDomesticDelivery>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(quotationDomesticDelivery: QuotationDomesticDelivery): Observable<EntityResponseType> {
        const copy = this.convert(quotationDomesticDelivery);
        return this.http.put<QuotationDomesticDelivery>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<QuotationDomesticDelivery>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<QuotationDomesticDelivery[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationDomesticDelivery[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationDomesticDelivery[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<QuotationDomesticDelivery[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationDomesticDelivery[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationDomesticDelivery[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<QuotationDomesticDelivery[]>> {
            const options = createRequestOption(req);
        return this.http.get<QuotationDomesticDelivery[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationDomesticDelivery[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: QuotationDomesticDelivery = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<QuotationDomesticDelivery[]>): HttpResponse<QuotationDomesticDelivery[]> {
        const jsonResponse: QuotationDomesticDelivery[] = res.body;
        const body: QuotationDomesticDelivery[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to QuotationDomesticDelivery.
     */
    private convertItemFromServer(quotationDomesticDelivery: QuotationDomesticDelivery): QuotationDomesticDelivery {
        const copy: QuotationDomesticDelivery = Object.assign({}, quotationDomesticDelivery);
        return copy;
    }

    /**
     * Convert a QuotationDomesticDelivery to a JSON which can be sent to the server.
     */
    private convert(quotationDomesticDelivery: QuotationDomesticDelivery): QuotationDomesticDelivery {
        const copy: QuotationDomesticDelivery = Object.assign({}, quotationDomesticDelivery);
        return copy;
    }
}
