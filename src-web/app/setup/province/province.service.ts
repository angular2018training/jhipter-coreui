import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Province } from './province.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Province>;

@Injectable()
export class ProvinceService {

    private resourceUrl =  SERVER_API_URL + 'api/provinces';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/provinces';

    constructor(private http: HttpClient) { }

    create(province: Province): Observable<EntityResponseType> {
        const copy = this.convert(province);
        return this.http.post<Province>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(province: Province): Observable<EntityResponseType> {
        const copy = this.convert(province);
        return this.http.put<Province>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Province>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Province[]>> {
        const options = createRequestOption(req);
        return this.http.get<Province[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Province[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<Province[]>> {
        const options = createRequestOption(req);
        return this.http.get<Province[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Province[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Province = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Province[]>): HttpResponse<Province[]> {
        const jsonResponse: Province[] = res.body;
        const body: Province[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Province.
     */
    private convertItemFromServer(province: Province): Province {
        const copy: Province = Object.assign({}, province);
        return copy;
    }

    /**
     * Convert a Province to a JSON which can be sent to the server.
     */
    private convert(province: Province): Province {
        const copy: Province = Object.assign({}, province);
        return copy;
    }
}
