import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { OrderServices } from '../model/order-services.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<OrderServices>;

@Injectable({ providedIn: 'root' })
export class OrderServicesService {

    private resourceUrl =  SERVER_API_URL + 'api/order-services';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/order-services';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/order-services';

    constructor(private http: HttpClient) { }

    create(orderServices: OrderServices): Observable<EntityResponseType> {
        const copy = this.convert(orderServices);
        return this.http.post<OrderServices>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(orderServices: OrderServices): Observable<EntityResponseType> {
        const copy = this.convert(orderServices);
        return this.http.put<OrderServices>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<OrderServices>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<OrderServices[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderServices[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderServices[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<OrderServices[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderServices[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderServices[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<OrderServices[]>> {
            const options = createRequestOption(req);
        return this.http.get<OrderServices[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderServices[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: OrderServices = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<OrderServices[]>): HttpResponse<OrderServices[]> {
        const jsonResponse: OrderServices[] = res.body;
        const body: OrderServices[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to OrderServices.
     */
    private convertItemFromServer(orderServices: OrderServices): OrderServices {
        const copy: OrderServices = Object.assign({}, orderServices);
        return copy;
    }

    /**
     * Convert a OrderServices to a JSON which can be sent to the server.
     */
    private convert(orderServices: OrderServices): OrderServices {
        const copy: OrderServices = Object.assign({}, orderServices);
        return copy;
    }
}
