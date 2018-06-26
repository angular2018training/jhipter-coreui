import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { PostOffice } from './post-office.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<PostOffice>;

@Injectable({ providedIn: 'root' })
export class PostOfficeService {

    private resourceUrl =  SERVER_API_URL + 'api/post-offices';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/post-offices';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/post-offices';

    constructor(private http: HttpClient) { }

    create(postOffice: PostOffice): Observable<EntityResponseType> {
        const copy = this.convert(postOffice);
        return this.http.post<PostOffice>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(postOffice: PostOffice): Observable<EntityResponseType> {
        const copy = this.convert(postOffice);
        return this.http.put<PostOffice>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<PostOffice>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<PostOffice[]>> {
        const options = createRequestOption(req);
        return this.http.get<PostOffice[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<PostOffice[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<PostOffice[]>> {
        const options = createRequestOption(req);
        return this.http.get<PostOffice[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<PostOffice[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<PostOffice[]>> {
            const options = createRequestOption(req);
        return this.http.get<PostOffice[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<PostOffice[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: PostOffice = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<PostOffice[]>): HttpResponse<PostOffice[]> {
        const jsonResponse: PostOffice[] = res.body;
        const body: PostOffice[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to PostOffice.
     */
    private convertItemFromServer(postOffice: PostOffice): PostOffice {
        const copy: PostOffice = Object.assign({}, postOffice);
        return copy;
    }

    /**
     * Convert a PostOffice to a JSON which can be sent to the server.
     */
    private convert(postOffice: PostOffice): PostOffice {
        const copy: PostOffice = Object.assign({}, postOffice);
        return copy;
    }
}
