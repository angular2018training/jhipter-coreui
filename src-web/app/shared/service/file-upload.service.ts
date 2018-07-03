import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { FileUpload } from '../model/file-upload.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<FileUpload>;

@Injectable({ providedIn: 'root' })
export class FileUploadService {

    private resourceUrl =  SERVER_API_URL + 'api/file-uploads';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/file-uploads';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/file-uploads';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(fileUpload: FileUpload): Observable<EntityResponseType> {
        const copy = this.convert(fileUpload);
        return this.http.post<FileUpload>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(fileUpload: FileUpload): Observable<EntityResponseType> {
        const copy = this.convert(fileUpload);
        return this.http.put<FileUpload>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<FileUpload>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<FileUpload[]>> {
        const options = createRequestOption(req);
        return this.http.get<FileUpload[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<FileUpload[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<FileUpload[]>> {
        const options = createRequestOption(req);
        return this.http.get<FileUpload[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<FileUpload[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<FileUpload[]>> {
            const options = createRequestOption(req);
        return this.http.get<FileUpload[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<FileUpload[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: FileUpload = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<FileUpload[]>): HttpResponse<FileUpload[]> {
        const jsonResponse: FileUpload[] = res.body;
        const body: FileUpload[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to FileUpload.
     */
    private convertItemFromServer(fileUpload: FileUpload): FileUpload {
        const copy: FileUpload = Object.assign({}, fileUpload);
        copy.uploadTime = this.dateUtils
            .convertDateTimeFromServer(fileUpload.uploadTime);
        return copy;
    }

    /**
     * Convert a FileUpload to a JSON which can be sent to the server.
     */
    private convert(fileUpload: FileUpload): FileUpload {
        const copy: FileUpload = Object.assign({}, fileUpload);

        copy.uploadTime = fileUpload.uploadTime != null && fileUpload.uploadTime.isValid() ? fileUpload.uploadTime.toJSON() : null;
        return copy;
    }
}
