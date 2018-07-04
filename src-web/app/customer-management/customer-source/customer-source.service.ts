import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { CustomerSource } from './customer-source.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CustomerSource>;

@Injectable({ providedIn: 'root' })
export class CustomerSourceService {

    private resourceUrl = SERVER_API_URL + 'api/customer-sources';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/customer-sources';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/customer-sources';

    constructor(private http: HttpClient) { }

    create(customerSource: CustomerSource): Observable<EntityResponseType> {
        const copy = this.convert(customerSource);
        return this.http.post<CustomerSource>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(customerSource: CustomerSource): Observable<EntityResponseType> {
        const copy = this.convert(customerSource);
        return this.http.put<CustomerSource>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CustomerSource>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CustomerSource[]>> {
        const options = createRequestOption(req);
        return this.http.get<CustomerSource[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerSource[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    search(req?: any): Observable<HttpResponse<CustomerSource[]>> {
        const options = createRequestOption(req);
        return this.http.get<CustomerSource[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerSource[]>) => this.convertArrayResponse(res));

    }
    searchExample(req?: any): Observable<HttpResponse<CustomerSource[]>> {
        const options = createRequestOption(req);
        return this.http.get<CustomerSource[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerSource[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CustomerSource = this.convertItemFromServer(res.body);
        return res.clone({ body });
    }

    private convertArrayResponse(res: HttpResponse<CustomerSource[]>): HttpResponse<CustomerSource[]> {
        const jsonResponse: CustomerSource[] = res.body;
        const body: CustomerSource[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({ body });
    }

    /**
     * Convert a returned JSON object to CustomerSource.
     */
    private convertItemFromServer(customerSource: CustomerSource): CustomerSource {
        const copy: CustomerSource = Object.assign({}, customerSource);
        return copy;
    }

    /**
     * Convert a CustomerSource to a JSON which can be sent to the server.
     */
    private convert(customerSource: CustomerSource): CustomerSource {
        const copy: CustomerSource = Object.assign({}, customerSource);
        return copy;
    }
}
