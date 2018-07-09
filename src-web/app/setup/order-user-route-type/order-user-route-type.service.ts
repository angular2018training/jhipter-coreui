import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { OrderUserRouteType } from './order-user-route-type.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<OrderUserRouteType>;

@Injectable({ providedIn: 'root' })
export class OrderUserRouteTypeService {

    private resourceUrl =  SERVER_API_URL + 'api/order-user-route-types';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/order-user-route-types';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/order-user-route-types';

    constructor(private http: HttpClient) { }

    create(orderUserRouteType: OrderUserRouteType): Observable<EntityResponseType> {
        const copy = this.convert(orderUserRouteType);
        return this.http.post<OrderUserRouteType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(orderUserRouteType: OrderUserRouteType): Observable<EntityResponseType> {
        const copy = this.convert(orderUserRouteType);
        return this.http.put<OrderUserRouteType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<OrderUserRouteType>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<OrderUserRouteType[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderUserRouteType[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderUserRouteType[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<OrderUserRouteType[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderUserRouteType[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderUserRouteType[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<OrderUserRouteType[]>> {
            const options = createRequestOption(req);
        return this.http.get<OrderUserRouteType[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderUserRouteType[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: OrderUserRouteType = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<OrderUserRouteType[]>): HttpResponse<OrderUserRouteType[]> {
        const jsonResponse: OrderUserRouteType[] = res.body;
        const body: OrderUserRouteType[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to OrderUserRouteType.
     */
    private convertItemFromServer(orderUserRouteType: OrderUserRouteType): OrderUserRouteType {
        const copy: OrderUserRouteType = Object.assign({}, orderUserRouteType);
        return copy;
    }

    /**
     * Convert a OrderUserRouteType to a JSON which can be sent to the server.
     */
    private convert(orderUserRouteType: OrderUserRouteType): OrderUserRouteType {
        const copy: OrderUserRouteType = Object.assign({}, orderUserRouteType);
        return copy;
    }
}
