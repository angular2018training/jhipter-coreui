import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { QuotationPickup } from '../model/quotation-pickup.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<QuotationPickup>;

@Injectable({ providedIn: 'root' })
export class QuotationPickupService {

    private resourceUrl =  SERVER_API_URL + 'api/quotation-pickups';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/quotation-pickups';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/quotation-pickups';

    constructor(private http: HttpClient) { }

    create(quotationPickup: QuotationPickup): Observable<EntityResponseType> {
        const copy = this.convert(quotationPickup);
        return this.http.post<QuotationPickup>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(quotationPickup: QuotationPickup): Observable<EntityResponseType> {
        const copy = this.convert(quotationPickup);
        return this.http.put<QuotationPickup>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<QuotationPickup>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<QuotationPickup[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationPickup[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationPickup[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<QuotationPickup[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationPickup[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationPickup[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<QuotationPickup[]>> {
            const options = createRequestOption(req);
        return this.http.get<QuotationPickup[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationPickup[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: QuotationPickup = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<QuotationPickup[]>): HttpResponse<QuotationPickup[]> {
        const jsonResponse: QuotationPickup[] = res.body;
        const body: QuotationPickup[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to QuotationPickup.
     */
    private convertItemFromServer(quotationPickup: QuotationPickup): QuotationPickup {
        const copy: QuotationPickup = Object.assign({}, quotationPickup);
        return copy;
    }

    /**
     * Convert a QuotationPickup to a JSON which can be sent to the server.
     */
    private convert(quotationPickup: QuotationPickup): QuotationPickup {
        const copy: QuotationPickup = Object.assign({}, quotationPickup);
        return copy;
    }
}
