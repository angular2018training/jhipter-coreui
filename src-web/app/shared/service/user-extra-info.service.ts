import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';

import { JhiDateUtils } from 'ng-jhipster';

import { UserExtraInfo } from '../model/user-extra-info.model';
import { createRequestOption } from '../../shared';

export type EntityResponseType = HttpResponse<UserExtraInfo>;

@Injectable({ providedIn: 'root' })
export class UserExtraInfoService {

    private resourceUrl =  SERVER_API_URL + 'api/user-extra-infos';
    private resourceSearchUrl = SERVER_API_URL + 'api/_search/user-extra-infos';
    private resourceSearchExampleUrl = SERVER_API_URL + 'api/_search_example/user-extra-infos';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils) { }

    create(userExtraInfo: UserExtraInfo): Observable<EntityResponseType> {
        const copy = this.convert(userExtraInfo);
        return this.http.post<UserExtraInfo>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(userExtraInfo: UserExtraInfo): Observable<EntityResponseType> {
        const copy = this.convert(userExtraInfo);
        return this.http.put<UserExtraInfo>(this.resourceUrl, copy, { observe: 'response' })
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<UserExtraInfo>(`${this.resourceUrl}/${id}`, { observe: 'response'})
            .map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<UserExtraInfo[]>> {
        const options = createRequestOption(req);
        return this.http.get<UserExtraInfo[]>(this.resourceUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UserExtraInfo[]>) => this.convertArrayResponse(res));
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response'});
    }

    search(req?: any): Observable<HttpResponse<UserExtraInfo[]>> {
        const options = createRequestOption(req);
        return this.http.get<UserExtraInfo[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UserExtraInfo[]>) => this.convertArrayResponse(res));

    }
    searchExample(req? : any): Observable<HttpResponse<UserExtraInfo[]>> {
            const options = createRequestOption(req);
        return this.http.get<UserExtraInfo[]>(this.resourceSearchExampleUrl, { params: options, observe: 'response' })
            .map((res: HttpResponse<UserExtraInfo[]>) => this.convertArrayResponse(res));
    }

    private convertResponse(res: EntityResponseType): EntityResponseType {
        const body: UserExtraInfo = this.convertItemFromServer(res.body);
        return res.clone({body});
    }

    private convertArrayResponse(res: HttpResponse<UserExtraInfo[]>): HttpResponse<UserExtraInfo[]> {
        const jsonResponse: UserExtraInfo[] = res.body;
        const body: UserExtraInfo[] = [];
        for (let i = 0; i < jsonResponse.length; i++) {
            body.push(this.convertItemFromServer(jsonResponse[i]));
        }
        return res.clone({body});
    }

    /**
     * Convert a returned JSON object to UserExtraInfo.
     */
    private convertItemFromServer(userExtraInfo: UserExtraInfo): UserExtraInfo {
        const copy: UserExtraInfo = Object.assign({}, userExtraInfo);
      copy.validDate = this.dateUtils
        .convertLocalDateFromServer(userExtraInfo.validDate);
        copy.lastLoginDate = this.dateUtils
            .convertDateTimeFromServer(userExtraInfo.lastLoginDate);
        copy.contractExpirationDate = this.dateUtils
            .convertDateTimeFromServer(userExtraInfo.contractExpirationDate);
        return copy;
    }

    /**
     * Convert a UserExtraInfo to a JSON which can be sent to the server.
     */
    private convert(userExtraInfo: UserExtraInfo): UserExtraInfo {
        const copy: UserExtraInfo = Object.assign({}, userExtraInfo);

      copy.validDate = this.dateUtils
        .convertLocalDateToServer(userExtraInfo.validDate);

        copy.lastLoginDate = userExtraInfo.lastLoginDate != null && userExtraInfo.lastLoginDate.isValid() ? userExtraInfo.lastLoginDate.toJSON() : null;

        copy.contractExpirationDate = userExtraInfo.contractExpirationDate != null && userExtraInfo.contractExpirationDate.isValid() ? userExtraInfo.contractExpirationDate.toJSON() : null;
        return copy;
    }
}
