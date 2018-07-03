import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { CustomerLegal } from '../model/customer-legal.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CustomerLegal>;

@Injectable({ providedIn: 'root' })
export class CustomerLegalService {

    private resourceUrl =  SERVER_API_URL + 'api/customer-legals';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/customer-legals';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/customer-legals';

    constructor(private http: HttpClient) { }

    create(customerLegal: CustomerLegal): Observable<EntityResponseType> {
        const copy = this.convert(customerLegal);
        return this.http.post<CustomerLegal>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(customerLegal: CustomerLegal): Observable<EntityResponseType> {
        const copy = this.convert(customerLegal);
        return this.http.put<CustomerLegal>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CustomerLegal>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CustomerLegal[]>> {
        const options = createRequestOption(req);
        return this.http.get<CustomerLegal[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerLegal[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<CustomerLegal[]>> {
        const options = createRequestOption(req);
        return this.http.get<CustomerLegal[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerLegal[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<CustomerLegal[]>> {
            const options = createRequestOption(req);
        return this.http.get<CustomerLegal[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerLegal[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CustomerLegal = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CustomerLegal[]>): HttpResponse<CustomerLegal[]> {
        const jsonResponse: CustomerLegal[] = res.body;
        const body: CustomerLegal[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CustomerLegal.
     */
    private convertItemFromServer(customerLegal: CustomerLegal): CustomerLegal {
        const copy: CustomerLegal = Object.assign({}, customerLegal);
        return copy;
    }

    /**
     * Convert a CustomerLegal to a JSON which can be sent to the server.
     */
    private convert(customerLegal: CustomerLegal): CustomerLegal {
        const copy: CustomerLegal = Object.assign({}, customerLegal);
        return copy;
    }
}
