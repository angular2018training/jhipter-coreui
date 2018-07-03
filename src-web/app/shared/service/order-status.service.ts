import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { OrderStatus } from '../model/order-status.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<OrderStatus>;

@Injectable({ providedIn: 'root' })
export class OrderStatusService {

    private resourceUrl =  SERVER_API_URL + 'api/order-statuses';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/order-statuses';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/order-statuses';

    constructor(private http: HttpClient) { }

    create(orderStatus: OrderStatus): Observable<EntityResponseType> {
        const copy = this.convert(orderStatus);
        return this.http.post<OrderStatus>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(orderStatus: OrderStatus): Observable<EntityResponseType> {
        const copy = this.convert(orderStatus);
        return this.http.put<OrderStatus>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<OrderStatus>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<OrderStatus[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderStatus[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderStatus[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<OrderStatus[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderStatus[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderStatus[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<OrderStatus[]>> {
            const options = createRequestOption(req);
        return this.http.get<OrderStatus[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderStatus[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: OrderStatus = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<OrderStatus[]>): HttpResponse<OrderStatus[]> {
        const jsonResponse: OrderStatus[] = res.body;
        const body: OrderStatus[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to OrderStatus.
     */
    private convertItemFromServer(orderStatus: OrderStatus): OrderStatus {
        const copy: OrderStatus = Object.assign({}, orderStatus);
        return copy;
    }

    /**
     * Convert a OrderStatus to a JSON which can be sent to the server.
     */
    private convert(orderStatus: OrderStatus): OrderStatus {
        const copy: OrderStatus = Object.assign({}, orderStatus);
        return copy;
    }
}
