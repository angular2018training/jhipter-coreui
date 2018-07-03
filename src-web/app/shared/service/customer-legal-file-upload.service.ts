import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { CustomerLegalFileUpload } from '../model/customer-legal-file-upload.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<CustomerLegalFileUpload>;

@Injectable({ providedIn: 'root' })
export class CustomerLegalFileUploadService {

    private resourceUrl =  SERVER_API_URL + 'api/customer-legal-file-uploads';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/customer-legal-file-uploads';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/customer-legal-file-uploads';

    constructor(private http: HttpClient) { }

    create(customerLegalFileUpload: CustomerLegalFileUpload): Observable<EntityResponseType> {
        const copy = this.convert(customerLegalFileUpload);
        return this.http.post<CustomerLegalFileUpload>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(customerLegalFileUpload: CustomerLegalFileUpload): Observable<EntityResponseType> {
        const copy = this.convert(customerLegalFileUpload);
        return this.http.put<CustomerLegalFileUpload>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<CustomerLegalFileUpload>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<CustomerLegalFileUpload[]>> {
        const options = createRequestOption(req);
        return this.http.get<CustomerLegalFileUpload[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerLegalFileUpload[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<CustomerLegalFileUpload[]>> {
        const options = createRequestOption(req);
        return this.http.get<CustomerLegalFileUpload[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerLegalFileUpload[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<CustomerLegalFileUpload[]>> {
            const options = createRequestOption(req);
        return this.http.get<CustomerLegalFileUpload[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<CustomerLegalFileUpload[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: CustomerLegalFileUpload = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<CustomerLegalFileUpload[]>): HttpResponse<CustomerLegalFileUpload[]> {
        const jsonResponse: CustomerLegalFileUpload[] = res.body;
        const body: CustomerLegalFileUpload[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to CustomerLegalFileUpload.
     */
    private convertItemFromServer(customerLegalFileUpload: CustomerLegalFileUpload): CustomerLegalFileUpload {
        const copy: CustomerLegalFileUpload = Object.assign({}, customerLegalFileUpload);
        return copy;
    }

    /**
     * Convert a CustomerLegalFileUpload to a JSON which can be sent to the server.
     */
    private convert(customerLegalFileUpload: CustomerLegalFileUpload): CustomerLegalFileUpload {
        const copy: CustomerLegalFileUpload = Object.assign({}, customerLegalFileUpload);
        return copy;
    }
}
