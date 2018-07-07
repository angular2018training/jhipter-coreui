import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { DistrictType } from '../model/district-type.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<DistrictType>;

@Injectable({ providedIn: 'root' })
export class DistrictTypeService {

    private resourceUrl =  SERVER_API_URL + 'api/district-types';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/district-types';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/district-types';

    constructor(private http: HttpClient) { }

    create(districtType: DistrictType): Observable<EntityResponseType> {
        const copy = this.convert(districtType);
        return this.http.post<DistrictType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(districtType: DistrictType): Observable<EntityResponseType> {
        const copy = this.convert(districtType);
        return this.http.put<DistrictType>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<DistrictType>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<DistrictType[]>> {
        const options = createRequestOption(req);
        return this.http.get<DistrictType[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<DistrictType[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<DistrictType[]>> {
        const options = createRequestOption(req);
        return this.http.get<DistrictType[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<DistrictType[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<DistrictType[]>> {
            const options = createRequestOption(req);
        return this.http.get<DistrictType[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<DistrictType[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: DistrictType = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<DistrictType[]>): HttpResponse<DistrictType[]> {
        const jsonResponse: DistrictType[] = res.body;
        const body: DistrictType[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to DistrictType.
     */
    private convertItemFromServer(districtType: DistrictType): DistrictType {
        const copy: DistrictType = Object.assign({}, districtType);
        return copy;
    }

    /**
     * Convert a DistrictType to a JSON which can be sent to the server.
     */
    private convert(districtType: DistrictType): DistrictType {
        const copy: DistrictType = Object.assign({}, districtType);
        return copy;
    }
}
