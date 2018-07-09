import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { District } from './district.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<District>;

@Injectable({ providedIn: 'root' })
export class DistrictService {

    private resourceUrl =  SERVER_API_URL + 'api/districts';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/districts';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/districts';

    constructor(private http: HttpClient) { }

    create(district: District): Observable<EntityResponseType> {
        const copy = this.convert(district);
        return this.http.post<District>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(district: District): Observable<EntityResponseType> {
        const copy = this.convert(district);
        return this.http.put<District>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<District>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<District[]>> {
        const options = createRequestOption(req);
        return this.http.get<District[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<District[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<District[]>> {
        const options = createRequestOption(req);
        return this.http.get<District[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<District[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<District[]>> {
            const options = createRequestOption(req);
        return this.http.get<District[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<District[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: District = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<District[]>): HttpResponse<District[]> {
        const jsonResponse: District[] = res.body;
        const body: District[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to District.
     */
    private convertItemFromServer(district: District): District {
        const copy: District = Object.assign({}, district);
        return copy;
    }

    /**
     * Convert a District to a JSON which can be sent to the server.
     */
    private convert(district: District): District {
        const copy: District = Object.assign({}, district);
        return copy;
    }
}
