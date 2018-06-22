import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Ward } from './ward.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Ward>;

@Injectable({ providedIn: 'root' })
export class WardService {

    private resourceUrl =  SERVER_API_URL + 'api/wards';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/wards';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/wards';

    constructor(private http: HttpClient) { }

    create(ward: Ward): Observable<EntityResponseType> {
        const copy = this.convert(ward);
        return this.http.post<Ward>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(ward: Ward): Observable<EntityResponseType> {
        const copy = this.convert(ward);
        return this.http.put<Ward>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Ward>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Ward[]>> {
        const options = createRequestOption(req);
        return this.http.get<Ward[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Ward[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<Ward[]>> {
        const options = createRequestOption(req);
        return this.http.get<Ward[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Ward[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<Ward[]>> {
            const options = createRequestOption(req);
        return this.http.get<Ward[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Ward[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Ward = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Ward[]>): HttpResponse<Ward[]> {
        const jsonResponse: Ward[] = res.body;
        const body: Ward[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Ward.
     */
    private convertItemFromServer(ward: Ward): Ward {
        const copy: Ward = Object.assign({}, ward);
        return copy;
    }

    /**
     * Convert a Ward to a JSON which can be sent to the server.
     */
    private convert(ward: Ward): Ward {
        const copy: Ward = Object.assign({}, ward);
        return copy;
    }
}
