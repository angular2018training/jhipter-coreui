import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { SERVER_API_URL } from '../../app.constants';
import { createRequestOption } from '../model/request-util';

@Injectable()
export class AccountService  {
    constructor(private http: HttpClient) { }

    get(req?:any): Observable<HttpResponse<Account>> {
        const options = createRequestOption(req);
        return this.http.get<Account>(SERVER_API_URL + 'api/account', { params: options,observe : 'response'});
    }

    save(account: any): Observable<HttpResponse<any>> {
        return this.http.post(SERVER_API_URL + 'api/account', account, {observe: 'response'});
    }
}
