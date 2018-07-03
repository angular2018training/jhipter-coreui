import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { OrderFee } from '../model/order-fee.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<OrderFee>;

@Injectable({ providedIn: 'root' })
export class OrderFeeService {

    private resourceUrl =  SERVER_API_URL + 'api/order-fees';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/order-fees';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/order-fees';

    constructor(private http: HttpClient) { }

    create(orderFee: OrderFee): Observable<EntityResponseType> {
        const copy = this.convert(orderFee);
        return this.http.post<OrderFee>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(orderFee: OrderFee): Observable<EntityResponseType> {
        const copy = this.convert(orderFee);
        return this.http.put<OrderFee>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<OrderFee>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<OrderFee[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderFee[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderFee[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<OrderFee[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderFee[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderFee[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<OrderFee[]>> {
            const options = createRequestOption(req);
        return this.http.get<OrderFee[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderFee[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: OrderFee = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<OrderFee[]>): HttpResponse<OrderFee[]> {
        const jsonResponse: OrderFee[] = res.body;
        const body: OrderFee[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to OrderFee.
     */
    private convertItemFromServer(orderFee: OrderFee): OrderFee {
        const copy: OrderFee = Object.assign({}, orderFee);
        return copy;
    }

    /**
     * Convert a OrderFee to a JSON which can be sent to the server.
     */
    private convert(orderFee: OrderFee): OrderFee {
        const copy: OrderFee = Object.assign({}, orderFee);
        return copy;
    }
}
