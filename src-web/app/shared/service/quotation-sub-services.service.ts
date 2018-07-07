import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { QuotationSubServices } from '../model/quotation-sub-services.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<QuotationSubServices>;

@Injectable({ providedIn: 'root' })
export class QuotationSubServicesService {

    private resourceUrl =  SERVER_API_URL + 'api/quotation-sub-services';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/quotation-sub-services';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/quotation-sub-services';

    constructor(private http: HttpClient) { }

    create(quotationSubServices: QuotationSubServices): Observable<EntityResponseType> {
        const copy = this.convert(quotationSubServices);
        return this.http.post<QuotationSubServices>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(quotationSubServices: QuotationSubServices): Observable<EntityResponseType> {
        const copy = this.convert(quotationSubServices);
        return this.http.put<QuotationSubServices>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<QuotationSubServices>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<QuotationSubServices[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationSubServices[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationSubServices[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<QuotationSubServices[]>> {
        const options = createRequestOption(req);
        return this.http.get<QuotationSubServices[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationSubServices[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<QuotationSubServices[]>> {
            const options = createRequestOption(req);
        return this.http.get<QuotationSubServices[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<QuotationSubServices[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: QuotationSubServices = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<QuotationSubServices[]>): HttpResponse<QuotationSubServices[]> {
        const jsonResponse: QuotationSubServices[] = res.body;
        const body: QuotationSubServices[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to QuotationSubServices.
     */
    private convertItemFromServer(quotationSubServices: QuotationSubServices): QuotationSubServices {
        const copy: QuotationSubServices = Object.assign({}, quotationSubServices);
        return copy;
    }

    /**
     * Convert a QuotationSubServices to a JSON which can be sent to the server.
     */
    private convert(quotationSubServices: QuotationSubServices): QuotationSubServices {
        const copy: QuotationSubServices = Object.assign({}, quotationSubServices);
        return copy;
    }
}
