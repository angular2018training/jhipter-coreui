import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { CustomerWarehouse } from '../model/customer-warehouse.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CustomerWarehouse>;

@Injectable({ providedIn: 'root' })
export class CustomerWarehouseService {

    private resourceUrl =  SERVER_API_URL + 'api/customer-warehouses';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/customer-warehouses';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/customer-warehouses';

    constructor(private http: HttpClient) { }

    create(customerWarehouse: CustomerWarehouse): Observable<EntityResponseType> {
        const copy = this.convert(customerWarehouse);
        return this.http.post<CustomerWarehouse>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(customerWarehouse: CustomerWarehouse): Observable<EntityResponseType> {
        const copy = this.convert(customerWarehouse);
        return this.http.put<CustomerWarehouse>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CustomerWarehouse>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CustomerWarehouse[]>> {
        const options = createRequestOption(req);
        return this.http.get<CustomerWarehouse[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerWarehouse[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<CustomerWarehouse[]>> {
        const options = createRequestOption(req);
        return this.http.get<CustomerWarehouse[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerWarehouse[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<CustomerWarehouse[]>> {
            const options = createRequestOption(req);
        return this.http.get<CustomerWarehouse[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerWarehouse[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CustomerWarehouse = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CustomerWarehouse[]>): HttpResponse<CustomerWarehouse[]> {
        const jsonResponse: CustomerWarehouse[] = res.body;
        const body: CustomerWarehouse[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CustomerWarehouse.
     */
    private convertItemFromServer(customerWarehouse: CustomerWarehouse): CustomerWarehouse {
        const copy: CustomerWarehouse = Object.assign({}, customerWarehouse);
        return copy;
    }

    /**
     * Convert a CustomerWarehouse to a JSON which can be sent to the server.
     */
    private convert(customerWarehouse: CustomerWarehouse): CustomerWarehouse {
        const copy: CustomerWarehouse = Object.assign({}, customerWarehouse);
        return copy;
    }
}
