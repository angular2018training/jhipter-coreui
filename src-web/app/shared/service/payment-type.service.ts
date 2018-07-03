import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { PaymentType } from '../model/payment-type.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<PaymentType>;

@Injectable({ providedIn: 'root' })
export class PaymentTypeService {

    private resourceUrl =  SERVER_API_URL + 'api/payment-types';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/payment-types';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/payment-types';

    constructor(private http: HttpClient) { }

    create(paymentType: PaymentType): Observable<EntityResponseType> {
        const copy = this.convert(paymentType);
        return this.http.post<PaymentType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(paymentType: PaymentType): Observable<EntityResponseType> {
        const copy = this.convert(paymentType);
        return this.http.put<PaymentType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<PaymentType>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<PaymentType[]>> {
        const options = createRequestOption(req);
        return this.http.get<PaymentType[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<PaymentType[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<PaymentType[]>> {
        const options = createRequestOption(req);
        return this.http.get<PaymentType[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<PaymentType[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<PaymentType[]>> {
            const options = createRequestOption(req);
        return this.http.get<PaymentType[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<PaymentType[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: PaymentType = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<PaymentType[]>): HttpResponse<PaymentType[]> {
        const jsonResponse: PaymentType[] = res.body;
        const body: PaymentType[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to PaymentType.
     */
    private convertItemFromServer(paymentType: PaymentType): PaymentType {
        const copy: PaymentType = Object.assign({}, paymentType);
        return copy;
    }

    /**
     * Convert a PaymentType to a JSON which can be sent to the server.
     */
    private convert(paymentType: PaymentType): PaymentType {
        const copy: PaymentType = Object.assign({}, paymentType);
        return copy;
    }
}
