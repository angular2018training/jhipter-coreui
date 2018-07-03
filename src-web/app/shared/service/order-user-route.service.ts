import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { OrderUserRoute } from '../model/order-user-route.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<OrderUserRoute>;

@Injectable({ providedIn: 'root' })
export class OrderUserRouteService {

    private resourceUrl =  SERVER_API_URL + 'api/order-user-routes';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/order-user-routes';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/order-user-routes';

    constructor(private http: HttpClient) { }

    create(orderUserRoute: OrderUserRoute): Observable<EntityResponseType> {
        const copy = this.convert(orderUserRoute);
        return this.http.post<OrderUserRoute>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(orderUserRoute: OrderUserRoute): Observable<EntityResponseType> {
        const copy = this.convert(orderUserRoute);
        return this.http.put<OrderUserRoute>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<OrderUserRoute>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<OrderUserRoute[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderUserRoute[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderUserRoute[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<OrderUserRoute[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderUserRoute[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderUserRoute[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<OrderUserRoute[]>> {
            const options = createRequestOption(req);
        return this.http.get<OrderUserRoute[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderUserRoute[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: OrderUserRoute = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<OrderUserRoute[]>): HttpResponse<OrderUserRoute[]> {
        const jsonResponse: OrderUserRoute[] = res.body;
        const body: OrderUserRoute[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to OrderUserRoute.
     */
    private convertItemFromServer(orderUserRoute: OrderUserRoute): OrderUserRoute {
        const copy: OrderUserRoute = Object.assign({}, orderUserRoute);
        return copy;
    }

    /**
     * Convert a OrderUserRoute to a JSON which can be sent to the server.
     */
    private convert(orderUserRoute: OrderUserRoute): OrderUserRoute {
        const copy: OrderUserRoute = Object.assign({}, orderUserRoute);
        return copy;
    }
}
