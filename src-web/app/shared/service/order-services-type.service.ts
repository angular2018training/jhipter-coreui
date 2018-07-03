import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { OrderServicesType } from '../model/order-services-type.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<OrderServicesType>;

@Injectable({ providedIn: 'root' })
export class OrderServicesTypeService {

    private resourceUrl =  SERVER_API_URL + 'api/order-services-types';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/order-services-types';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/order-services-types';

    constructor(private http: HttpClient) { }

    create(orderServicesType: OrderServicesType): Observable<EntityResponseType> {
        const copy = this.convert(orderServicesType);
        return this.http.post<OrderServicesType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(orderServicesType: OrderServicesType): Observable<EntityResponseType> {
        const copy = this.convert(orderServicesType);
        return this.http.put<OrderServicesType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<OrderServicesType>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<OrderServicesType[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderServicesType[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderServicesType[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<OrderServicesType[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderServicesType[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderServicesType[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<OrderServicesType[]>> {
            const options = createRequestOption(req);
        return this.http.get<OrderServicesType[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderServicesType[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: OrderServicesType = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<OrderServicesType[]>): HttpResponse<OrderServicesType[]> {
        const jsonResponse: OrderServicesType[] = res.body;
        const body: OrderServicesType[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to OrderServicesType.
     */
    private convertItemFromServer(orderServicesType: OrderServicesType): OrderServicesType {
        const copy: OrderServicesType = Object.assign({}, orderServicesType);
        return copy;
    }

    /**
     * Convert a OrderServicesType to a JSON which can be sent to the server.
     */
    private convert(orderServicesType: OrderServicesType): OrderServicesType {
        const copy: OrderServicesType = Object.assign({}, orderServicesType);
        return copy;
    }
}
