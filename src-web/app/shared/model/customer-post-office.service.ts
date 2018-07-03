import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { CustomerPostOffice } from './customer-post-office.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CustomerPostOffice>;

@Injectable({ providedIn: 'root' })
export class CustomerPostOfficeService {

    private resourceUrl =  SERVER_API_URL + 'api/customer-post-offices';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/customer-post-offices';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/customer-post-offices';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(customerPostOffice: CustomerPostOffice): Observable<EntityResponseType> {
        const copy = this.convert(customerPostOffice);
        return this.http.post<CustomerPostOffice>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(customerPostOffice: CustomerPostOffice): Observable<EntityResponseType> {
        const copy = this.convert(customerPostOffice);
        return this.http.put<CustomerPostOffice>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CustomerPostOffice>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CustomerPostOffice[]>> {
        const options = createRequestOption(req);
        return this.http.get<CustomerPostOffice[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerPostOffice[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<CustomerPostOffice[]>> {
        const options = createRequestOption(req);
        return this.http.get<CustomerPostOffice[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerPostOffice[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<CustomerPostOffice[]>> {
            const options = createRequestOption(req);
        return this.http.get<CustomerPostOffice[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerPostOffice[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CustomerPostOffice = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CustomerPostOffice[]>): HttpResponse<CustomerPostOffice[]> {
        const jsonResponse: CustomerPostOffice[] = res.body;
        const body: CustomerPostOffice[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CustomerPostOffice.
     */
    private convertItemFromServer(customerPostOffice: CustomerPostOffice): CustomerPostOffice {
        const copy: CustomerPostOffice = Object.assign({}, customerPostOffice);
        copy.createDate = this.dateUtils
            .convertDateTimeFromServer(customerPostOffice.createDate);
        return copy;
    }

    /**
     * Convert a CustomerPostOffice to a JSON which can be sent to the server.
     */
    private convert(customerPostOffice: CustomerPostOffice): CustomerPostOffice {
        const copy: CustomerPostOffice = Object.assign({}, customerPostOffice);

        copy.createDate = customerPostOffice.createDate != null && customerPostOffice.createDate.isValid() ? customerPostOffice.createDate.toJSON() : null;
        return copy;
    }
}
