import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { CustomerType } from './customer-type.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CustomerType>;

@Injectable({ providedIn: 'root' })
export class CustomerTypeService {

    private resourceUrl =  SERVER_API_URL + 'api/customer-types';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/customer-types';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/customer-types';

    constructor(private http: HttpClient) { }

    create(customerType: CustomerType): Observable<EntityResponseType> {
        const copy = this.convert(customerType);
        return this.http.post<CustomerType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(customerType: CustomerType): Observable<EntityResponseType> {
        const copy = this.convert(customerType);
        return this.http.put<CustomerType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CustomerType>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CustomerType[]>> {
        const options = createRequestOption(req);
        return this.http.get<CustomerType[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerType[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<CustomerType[]>> {
        const options = createRequestOption(req);
        return this.http.get<CustomerType[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerType[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<CustomerType[]>> {
            const options = createRequestOption(req);
        return this.http.get<CustomerType[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerType[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CustomerType = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CustomerType[]>): HttpResponse<CustomerType[]> {
        const jsonResponse: CustomerType[] = res.body;
        const body: CustomerType[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CustomerType.
     */
    private convertItemFromServer(customerType: CustomerType): CustomerType {
        const copy: CustomerType = Object.assign({}, customerType);
        return copy;
    }

    /**
     * Convert a CustomerType to a JSON which can be sent to the server.
     */
    private convert(customerType: CustomerType): CustomerType {
        const copy: CustomerType = Object.assign({}, customerType);
        return copy;
    }
}
