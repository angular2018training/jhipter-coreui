import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { QuotationInsurance } from '../model/quotation-insurance.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<QuotationInsurance>;

@Injectable({ providedIn: 'root' })
export class QuotationInsuranceService {

    private resourceUrl =  SERVER_API_URL + 'api/quotation-insurances';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/quotation-insurances';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/quotation-insurances';

    constructor(private http: HttpClient) { }

    create(quotationInsurance: QuotationInsurance): Observable<EntityResponseType> {
        const copy = this.convert(quotationInsurance);
        return this.http.post<QuotationInsurance>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(quotationInsurance: QuotationInsurance): Observable<EntityResponseType> {
        const copy = this.convert(quotationInsurance);
        return this.http.put<QuotationInsurance>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<QuotationInsurance>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<QuotationInsurance[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationInsurance[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationInsurance[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<QuotationInsurance[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationInsurance[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationInsurance[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<QuotationInsurance[]>> {
            const options = createRequestOption(req);
        return this.http.get<QuotationInsurance[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationInsurance[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: QuotationInsurance = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<QuotationInsurance[]>): HttpResponse<QuotationInsurance[]> {
        const jsonResponse: QuotationInsurance[] = res.body;
        const body: QuotationInsurance[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to QuotationInsurance.
     */
    private convertItemFromServer(quotationInsurance: QuotationInsurance): QuotationInsurance {
        const copy: QuotationInsurance = Object.assign({}, quotationInsurance);
        return copy;
    }

    /**
     * Convert a QuotationInsurance to a JSON which can be sent to the server.
     */
    private convert(quotationInsurance: QuotationInsurance): QuotationInsurance {
        const copy: QuotationInsurance = Object.assign({}, quotationInsurance);
        return copy;
    }
}
