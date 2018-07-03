import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { OrderMain } from './order-main.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<OrderMain>;

@Injectable({ providedIn: 'root' })
export class OrderMainService {

    private resourceUrl =  SERVER_API_URL + 'api/order-mains';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/order-mains';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/order-mains';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(orderMain: OrderMain): Observable<EntityResponseType> {
        const copy = this.convert(orderMain);
        return this.http.post<OrderMain>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(orderMain: OrderMain): Observable<EntityResponseType> {
        const copy = this.convert(orderMain);
        return this.http.put<OrderMain>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<OrderMain>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<OrderMain[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderMain[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderMain[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<OrderMain[]>> {
        const options = createRequestOption(req);
        return this.http.get<OrderMain[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderMain[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<OrderMain[]>> {
            const options = createRequestOption(req);
        return this.http.get<OrderMain[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<OrderMain[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: OrderMain = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<OrderMain[]>): HttpResponse<OrderMain[]> {
        const jsonResponse: OrderMain[] = res.body;
        const body: OrderMain[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to OrderMain.
     */
    private convertItemFromServer(orderMain: OrderMain): OrderMain {
        const copy: OrderMain = Object.assign({}, orderMain);
        copy.createDate = this.dateUtils
            .convertDateTimeFromServer(orderMain.createDate);
        copy.sendDate = this.dateUtils
            .convertDateTimeFromServer(orderMain.sendDate);
        return copy;
    }

    /**
     * Convert a OrderMain to a JSON which can be sent to the server.
     */
    private convert(orderMain: OrderMain): OrderMain {
        const copy: OrderMain = Object.assign({}, orderMain);

        copy.createDate = orderMain.createDate != null && orderMain.createDate.isValid() ? orderMain.createDate.toJSON() : null;

        copy.sendDate = orderMain.sendDate != null && orderMain.sendDate.isValid() ? orderMain.sendDate.toJSON() : null;
        return copy;
    }
}
