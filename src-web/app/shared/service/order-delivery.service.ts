import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { OrderDelivery } from '../model/order-delivery.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<OrderDelivery>;

@Injectable({ providedIn: 'root' })
export class OrderDeliveryService {

    private resourceUrl =  SERVER_API_URL + 'api/order-deliveries';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/order-deliveries';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/order-deliveries';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(orderDelivery: OrderDelivery): Observable<EntityResponseType> {
        const copy = this.convert(orderDelivery);
        return this.http.post<OrderDelivery>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(orderDelivery: OrderDelivery): Observable<EntityResponseType> {
        const copy = this.convert(orderDelivery);
        return this.http.put<OrderDelivery>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<OrderDelivery>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<OrderDelivery[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderDelivery[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderDelivery[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<OrderDelivery[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderDelivery[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderDelivery[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<OrderDelivery[]>> {
            const options = createRequestOption(req);
        return this.http.get<OrderDelivery[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderDelivery[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: OrderDelivery = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<OrderDelivery[]>): HttpResponse<OrderDelivery[]> {
        const jsonResponse: OrderDelivery[] = res.body;
        const body: OrderDelivery[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to OrderDelivery.
     */
    private convertItemFromServer(orderDelivery: OrderDelivery): OrderDelivery {
        const copy: OrderDelivery = Object.assign({}, orderDelivery);
        copy.receiveTime = this.dateUtils
            .convertDateTimeFromServer(orderDelivery.receiveTime);
        copy.createDate = this.dateUtils
            .convertDateTimeFromServer(orderDelivery.createDate);
        return copy;
    }

    /**
     * Convert a OrderDelivery to a JSON which can be sent to the server.
     */
    private convert(orderDelivery: OrderDelivery): OrderDelivery {
        const copy: OrderDelivery = Object.assign({}, orderDelivery);

        copy.receiveTime = orderDelivery.receiveTime != null && orderDelivery.receiveTime.isValid() ? orderDelivery.receiveTime.toJSON() : null;

        copy.createDate = orderDelivery.createDate != null && orderDelivery.createDate.isValid() ? orderDelivery.createDate.toJSON() : null;
        return copy;
    }
}
