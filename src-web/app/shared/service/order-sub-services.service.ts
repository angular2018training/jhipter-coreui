import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { OrderSubServices } from '../model/order-sub-services.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<OrderSubServices>;

@Injectable({ providedIn: 'root' })
export class OrderSubServicesService {

    private resourceUrl =  SERVER_API_URL + 'api/order-sub-services';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/order-sub-services';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/order-sub-services';

    constructor(private http: HttpClient) { }

    create(orderSubServices: OrderSubServices): Observable<EntityResponseType> {
        const copy = this.convert(orderSubServices);
        return this.http.post<OrderSubServices>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(orderSubServices: OrderSubServices): Observable<EntityResponseType> {
        const copy = this.convert(orderSubServices);
        return this.http.put<OrderSubServices>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<OrderSubServices>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<OrderSubServices[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderSubServices[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderSubServices[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<OrderSubServices[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderSubServices[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderSubServices[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<OrderSubServices[]>> {
            const options = createRequestOption(req);
        return this.http.get<OrderSubServices[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderSubServices[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: OrderSubServices = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<OrderSubServices[]>): HttpResponse<OrderSubServices[]> {
        const jsonResponse: OrderSubServices[] = res.body;
        const body: OrderSubServices[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to OrderSubServices.
     */
    private convertItemFromServer(orderSubServices: OrderSubServices): OrderSubServices {
        const copy: OrderSubServices = Object.assign({}, orderSubServices);
        return copy;
    }

    /**
     * Convert a OrderSubServices to a JSON which can be sent to the server.
     */
    private convert(orderSubServices: OrderSubServices): OrderSubServices {
        const copy: OrderSubServices = Object.assign({}, orderSubServices);
        return copy;
    }
}
