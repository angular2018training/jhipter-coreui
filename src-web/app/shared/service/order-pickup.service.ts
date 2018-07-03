import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { OrderPickup } from '../model/order-pickup.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<OrderPickup>;

@Injectable({ providedIn: 'root' })
export class OrderPickupService {

    private resourceUrl =  SERVER_API_URL + 'api/order-pickups';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/order-pickups';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/order-pickups';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(orderPickup: OrderPickup): Observable<EntityResponseType> {
        const copy = this.convert(orderPickup);
        return this.http.post<OrderPickup>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(orderPickup: OrderPickup): Observable<EntityResponseType> {
        const copy = this.convert(orderPickup);
        return this.http.put<OrderPickup>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<OrderPickup>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<OrderPickup[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderPickup[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderPickup[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<OrderPickup[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderPickup[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderPickup[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<OrderPickup[]>> {
            const options = createRequestOption(req);
        return this.http.get<OrderPickup[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderPickup[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: OrderPickup = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<OrderPickup[]>): HttpResponse<OrderPickup[]> {
        const jsonResponse: OrderPickup[] = res.body;
        const body: OrderPickup[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to OrderPickup.
     */
    private convertItemFromServer(orderPickup: OrderPickup): OrderPickup {
        const copy: OrderPickup = Object.assign({}, orderPickup);
        copy.assignDate = this.dateUtils
            .convertDateTimeFromServer(orderPickup.assignDate);
        copy.pickupDate = this.dateUtils
            .convertDateTimeFromServer(orderPickup.pickupDate);
        return copy;
    }

    /**
     * Convert a OrderPickup to a JSON which can be sent to the server.
     */
    private convert(orderPickup: OrderPickup): OrderPickup {
        const copy: OrderPickup = Object.assign({}, orderPickup);

        copy.assignDate = orderPickup.assignDate != null && orderPickup.assignDate.isValid() ? orderPickup.assignDate.toJSON() : null;

        copy.pickupDate = orderPickup.pickupDate != null && orderPickup.pickupDate.isValid() ? orderPickup.pickupDate.toJSON() : null;
        return copy;
    }
}
