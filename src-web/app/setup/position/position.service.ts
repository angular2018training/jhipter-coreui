import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { Position } from './position.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Position>;

@Injectable({ providedIn: 'root' })
export class PositionService {

    private resourceUrl =  SERVER_API_URL + 'api/positions';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/positions';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/positions';

    constructor(private http: HttpClient) { }

    create(position: Position): Observable<EntityResponseType> {
        const copy = this.convert(position);
        return this.http.post<Position>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(position: Position): Observable<EntityResponseType> {
        const copy = this.convert(position);
        return this.http.put<Position>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Position>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Position[]>> {
        const options = createRequestOption(req);
        return this.http.get<Position[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Position[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<Position[]>> {
        const options = createRequestOption(req);
        return this.http.get<Position[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Position[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<Position[]>> {
            const options = createRequestOption(req);
        return this.http.get<Position[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Position[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Position = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Position[]>): HttpResponse<Position[]> {
        const jsonResponse: Position[] = res.body;
        const body: Position[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Position.
     */
    private convertItemFromServer(position: Position): Position {
        const copy: Position = Object.assign({}, position);
        return copy;
    }

    /**
     * Convert a Position to a JSON which can be sent to the server.
     */
    private convert(position: Position): Position {
        const copy: Position = Object.assign({}, position);
        return copy;
    }
}
