import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Warehouse } from '../model/warehouse.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Warehouse>;

@Injectable({ providedIn: 'root' })
export class WarehouseService {

    private resourceUrl =  SERVER_API_URL + 'api/warehouses';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/warehouses';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/warehouses';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(warehouse: Warehouse): Observable<EntityResponseType> {
        const copy = this.convert(warehouse);
        return this.http.post<Warehouse>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(warehouse: Warehouse): Observable<EntityResponseType> {
        const copy = this.convert(warehouse);
        return this.http.put<Warehouse>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Warehouse>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Warehouse[]>> {
        const options = createRequestOption(req);
        return this.http.get<Warehouse[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Warehouse[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<Warehouse[]>> {
        const options = createRequestOption(req);
        return this.http.get<Warehouse[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Warehouse[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<Warehouse[]>> {
            const options = createRequestOption(req);
        return this.http.get<Warehouse[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Warehouse[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Warehouse = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Warehouse[]>): HttpResponse<Warehouse[]> {
        const jsonResponse: Warehouse[] = res.body;
        const body: Warehouse[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Warehouse.
     */
    private convertItemFromServer(warehouse: Warehouse): Warehouse {
        const copy: Warehouse = Object.assign({}, warehouse);
        copy.createDate = this.dateUtils
            .convertDateTimeFromServer(warehouse.createDate);
        return copy;
    }

    /**
     * Convert a Warehouse to a JSON which can be sent to the server.
     */
    private convert(warehouse: Warehouse): Warehouse {
        const copy: Warehouse = Object.assign({}, warehouse);

        copy.createDate = warehouse.createDate != null && warehouse.createDate.isValid() ? warehouse.createDate.toJSON() : null;
        return copy;
    }
}
