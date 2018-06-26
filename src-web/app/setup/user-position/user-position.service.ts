import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { UserPosition } from './user-position.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<UserPosition>;

@Injectable({ providedIn: 'root' })
export class UserPositionService {

    private resourceUrl =  SERVER_API_URL + 'api/user-positions';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/user-positions';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/user-positions';

    constructor(private http: HttpClient) { }

    create(userPosition: UserPosition): Observable<EntityResponseType> {
        const copy = this.convert(userPosition);
        return this.http.post<UserPosition>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(userPosition: UserPosition): Observable<EntityResponseType> {
        const copy = this.convert(userPosition);
        return this.http.put<UserPosition>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<UserPosition>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<UserPosition[]>> {
        const options = createRequestOption(req);
        return this.http.get<UserPosition[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UserPosition[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<UserPosition[]>> {
        const options = createRequestOption(req);
        return this.http.get<UserPosition[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UserPosition[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<UserPosition[]>> {
            const options = createRequestOption(req);
        return this.http.get<UserPosition[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UserPosition[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: UserPosition = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<UserPosition[]>): HttpResponse<UserPosition[]> {
        const jsonResponse: UserPosition[] = res.body;
        const body: UserPosition[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to UserPosition.
     */
    private convertItemFromServer(userPosition: UserPosition): UserPosition {
        const copy: UserPosition = Object.assign({}, userPosition);
        return copy;
    }

    /**
     * Convert a UserPosition to a JSON which can be sent to the server.
     */
    private convert(userPosition: UserPosition): UserPosition {
        const copy: UserPosition = Object.assign({}, userPosition);
        return copy;
    }
}
