import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { OrderSubServicesType } from './order-sub-services-type.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<OrderSubServicesType>;

@Injectable({ providedIn: 'root' })
export class OrderSubServicesTypeService {

    private resourceUrl =  SERVER_API_URL + 'api/order-sub-services-types';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/order-sub-services-types';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/order-sub-services-types';

    constructor(private http: HttpClient) { }

    create(orderSubServicesType: OrderSubServicesType): Observable<EntityResponseType> {
        const copy = this.convert(orderSubServicesType);
        return this.http.post<OrderSubServicesType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(orderSubServicesType: OrderSubServicesType): Observable<EntityResponseType> {
        const copy = this.convert(orderSubServicesType);
        return this.http.put<OrderSubServicesType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<OrderSubServicesType>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<OrderSubServicesType[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderSubServicesType[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderSubServicesType[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<OrderSubServicesType[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderSubServicesType[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderSubServicesType[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<OrderSubServicesType[]>> {
            const options = createRequestOption(req);
        return this.http.get<OrderSubServicesType[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderSubServicesType[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: OrderSubServicesType = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<OrderSubServicesType[]>): HttpResponse<OrderSubServicesType[]> {
        const jsonResponse: OrderSubServicesType[] = res.body;
        const body: OrderSubServicesType[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to OrderSubServicesType.
     */
    private convertItemFromServer(orderSubServicesType: OrderSubServicesType): OrderSubServicesType {
        const copy: OrderSubServicesType = Object.assign({}, orderSubServicesType);
        return copy;
    }

    /**
     * Convert a OrderSubServicesType to a JSON which can be sent to the server.
     */
    private convert(orderSubServicesType: OrderSubServicesType): OrderSubServicesType {
        const copy: OrderSubServicesType = Object.assign({}, orderSubServicesType);
        return copy;
    }
}
