import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { CustomerServices } from '../model/customer-services.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CustomerServices>;

@Injectable({ providedIn: 'root' })
export class CustomerServicesService {

    private resourceUrl =  SERVER_API_URL + 'api/customer-services';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/customer-services';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/customer-services';

    constructor(private http: HttpClient) { }

    create(customerServices: CustomerServices): Observable<EntityResponseType> {
        const copy = this.convert(customerServices);
        return this.http.post<CustomerServices>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(customerServices: CustomerServices): Observable<EntityResponseType> {
        const copy = this.convert(customerServices);
        return this.http.put<CustomerServices>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CustomerServices>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CustomerServices[]>> {
        const options = createRequestOption(req);
        return this.http.get<CustomerServices[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerServices[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<CustomerServices[]>> {
        const options = createRequestOption(req);
        return this.http.get<CustomerServices[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerServices[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<CustomerServices[]>> {
            const options = createRequestOption(req);
        return this.http.get<CustomerServices[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerServices[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CustomerServices = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CustomerServices[]>): HttpResponse<CustomerServices[]> {
        const jsonResponse: CustomerServices[] = res.body;
        const body: CustomerServices[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CustomerServices.
     */
    private convertItemFromServer(customerServices: CustomerServices): CustomerServices {
        const copy: CustomerServices = Object.assign({}, customerServices);
        return copy;
    }

    /**
     * Convert a CustomerServices to a JSON which can be sent to the server.
     */
    private convert(customerServices: CustomerServices): CustomerServices {
        const copy: CustomerServices = Object.assign({}, customerServices);
        return copy;
    }
}
