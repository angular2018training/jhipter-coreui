import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Region } from './region.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Region>;

@Injectable({ providedIn: 'root' })
export class RegionService {

    private resourceUrl =  SERVER_API_URL + 'api/regions';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/regions';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/regions';

    constructor(private http: HttpClient) { }

    create(region: Region): Observable<EntityResponseType> {
        const copy = this.convert(region);
        return this.http.post<Region>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(region: Region): Observable<EntityResponseType> {
        const copy = this.convert(region);
        return this.http.put<Region>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Region>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Region[]>> {
        const options = createRequestOption(req);
        return this.http.get<Region[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Region[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<Region[]>> {
        const options = createRequestOption(req);
        return this.http.get<Region[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Region[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<Region[]>> {
            const options = createRequestOption(req);
        return this.http.get<Region[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Region[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Region = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Region[]>): HttpResponse<Region[]> {
        const jsonResponse: Region[] = res.body;
        const body: Region[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Region.
     */
    private convertItemFromServer(region: Region): Region {
        const copy: Region = Object.assign({}, region);
        return copy;
    }

    /**
     * Convert a Region to a JSON which can be sent to the server.
     */
    private convert(region: Region): Region {
        const copy: Region = Object.assign({}, region);
        return copy;
    }
}
