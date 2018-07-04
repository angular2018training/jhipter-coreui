import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { CustomerPayment } from './customer-payment.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CustomerPayment>;

@Injectable({ providedIn: 'root' })
export class CustomerPaymentService {

    private resourceUrl =  SERVER_API_URL + 'api/customer-payments';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/customer-payments';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/customer-payments';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(customerPayment: CustomerPayment, id: number): Observable<EntityResponseType> {
        const copy = this.convert(customerPayment);
        return this.http.post<CustomerPayment>(`${this.resourceUrl}?id=${id}`, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(customerPayment: CustomerPayment, id: number): Observable<EntityResponseType> {
        const copy = this.convert(customerPayment);
        return this.http.put<CustomerPayment>(`${this.resourceUrl}?id=${id}`, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CustomerPayment>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CustomerPayment[]>> {
        const options = createRequestOption(req);
        return this.http.get<CustomerPayment[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerPayment[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<CustomerPayment[]>> {
        const options = createRequestOption(req);
        return this.http.get<CustomerPayment[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerPayment[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<CustomerPayment[]>> {
            const options = createRequestOption(req);
        return this.http.get<CustomerPayment[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerPayment[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CustomerPayment = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CustomerPayment[]>): HttpResponse<CustomerPayment[]> {
        const jsonResponse: CustomerPayment[] = res.body;
        const body: CustomerPayment[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CustomerPayment.
     */
    private convertItemFromServer(customerPayment: CustomerPayment): CustomerPayment {
        const copy: CustomerPayment = Object.assign({}, customerPayment);
        copy.dateVerify = this.dateUtils
            .convertDateTimeFromServer(customerPayment.dateVerify);
        return copy;
    }

    /**
     * Convert a CustomerPayment to a JSON which can be sent to the server.
     */
    private convert(customerPayment: CustomerPayment): CustomerPayment {
        const copy: CustomerPayment = Object.assign({}, customerPayment);

        copy.dateVerify = customerPayment.dateVerify != null && customerPayment.dateVerify.isValid() ? customerPayment.dateVerify.toJSON() : null;
        return copy;
    }
}
