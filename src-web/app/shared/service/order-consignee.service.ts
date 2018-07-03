import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { OrderConsignee } from '../model/order-consignee.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<OrderConsignee>;

@Injectable({ providedIn: 'root' })
export class OrderConsigneeService {

    private resourceUrl =  SERVER_API_URL + 'api/order-consignees';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/order-consignees';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/order-consignees';

    constructor(private http: HttpClient) { }

    create(orderConsignee: OrderConsignee): Observable<EntityResponseType> {
        const copy = this.convert(orderConsignee);
        return this.http.post<OrderConsignee>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(orderConsignee: OrderConsignee): Observable<EntityResponseType> {
        const copy = this.convert(orderConsignee);
        return this.http.put<OrderConsignee>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<OrderConsignee>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<OrderConsignee[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderConsignee[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderConsignee[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<OrderConsignee[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderConsignee[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderConsignee[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<OrderConsignee[]>> {
            const options = createRequestOption(req);
        return this.http.get<OrderConsignee[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderConsignee[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: OrderConsignee = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<OrderConsignee[]>): HttpResponse<OrderConsignee[]> {
        const jsonResponse: OrderConsignee[] = res.body;
        const body: OrderConsignee[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to OrderConsignee.
     */
    private convertItemFromServer(orderConsignee: OrderConsignee): OrderConsignee {
        const copy: OrderConsignee = Object.assign({}, orderConsignee);
        return copy;
    }

    /**
     * Convert a OrderConsignee to a JSON which can be sent to the server.
     */
    private convert(orderConsignee: OrderConsignee): OrderConsignee {
        const copy: OrderConsignee = Object.assign({}, orderConsignee);
        return copy;
    }
}
