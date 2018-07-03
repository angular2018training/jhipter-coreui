import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { Quotation } from '../model/quotation.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<Quotation>;

@Injectable({ providedIn: 'root' })
export class QuotationService {

    private resourceUrl =  SERVER_API_URL + 'api/quotations';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/quotations';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/quotations';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(quotation: Quotation): Observable<EntityResponseType> {
        const copy = this.convert(quotation);
        return this.http.post<Quotation>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(quotation: Quotation): Observable<EntityResponseType> {
        const copy = this.convert(quotation);
        return this.http.put<Quotation>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<Quotation>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<Quotation[]>> {
        const options = createRequestOption(req);
        return this.http.get<Quotation[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Quotation[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<Quotation[]>> {
        const options = createRequestOption(req);
        return this.http.get<Quotation[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Quotation[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<Quotation[]>> {
            const options = createRequestOption(req);
        return this.http.get<Quotation[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<Quotation[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: Quotation = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<Quotation[]>): HttpResponse<Quotation[]> {
        const jsonResponse: Quotation[] = res.body;
        const body: Quotation[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to Quotation.
     */
    private convertItemFromServer(quotation: Quotation): Quotation {
        const copy: Quotation = Object.assign({}, quotation);
        copy.createDate = this.dateUtils
            .convertDateTimeFromServer(quotation.createDate);
        return copy;
    }

    /**
     * Convert a Quotation to a JSON which can be sent to the server.
     */
    private convert(quotation: Quotation): Quotation {
        const copy: Quotation = Object.assign({}, quotation);

        copy.createDate = quotation.createDate != null && quotation.createDate.isValid() ? quotation.createDate.toJSON() : null;
        return copy;
    }
}
