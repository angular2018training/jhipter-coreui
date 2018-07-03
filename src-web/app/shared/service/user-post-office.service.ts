import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { UserPostOffice } from '../model/user-post-office.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<UserPostOffice>;

@Injectable({ providedIn: 'root' })
export class UserPostOfficeService {

    private resourceUrl =  SERVER_API_URL + 'api/user-post-offices';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/user-post-offices';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/user-post-offices';

    constructor(private http: HttpClient) { }

    create(userPostOffice: UserPostOffice): Observable<EntityResponseType> {
        const copy = this.convert(userPostOffice);
        return this.http.post<UserPostOffice>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(userPostOffice: UserPostOffice): Observable<EntityResponseType> {
        const copy = this.convert(userPostOffice);
        return this.http.put<UserPostOffice>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<UserPostOffice>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<UserPostOffice[]>> {
        const options = createRequestOption(req);
        return this.http.get<UserPostOffice[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UserPostOffice[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<UserPostOffice[]>> {
        const options = createRequestOption(req);
        return this.http.get<UserPostOffice[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UserPostOffice[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<UserPostOffice[]>> {
            const options = createRequestOption(req);
        return this.http.get<UserPostOffice[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UserPostOffice[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: UserPostOffice = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<UserPostOffice[]>): HttpResponse<UserPostOffice[]> {
        const jsonResponse: UserPostOffice[] = res.body;
        const body: UserPostOffice[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to UserPostOffice.
     */
    private convertItemFromServer(userPostOffice: UserPostOffice): UserPostOffice {
        const copy: UserPostOffice = Object.assign({}, userPostOffice);
        return copy;
    }

    /**
     * Convert a UserPostOffice to a JSON which can be sent to the server.
     */
    private convert(userPostOffice: UserPostOffice): UserPostOffice {
        const copy: UserPostOffice = Object.assign({}, userPostOffice);
        return copy;
    }
}
