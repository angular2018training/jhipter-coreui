import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { SERVER_API_URL } from '../../app.constants';
import { User } from './user.model';
import { createRequestOption } from '../model/request-util';
import {JhiDateUtils} from "ng-jhipster";
import {UserExtraInfoService} from "../service/user-extra-info.service";
export type EntityResponseType = HttpResponse<User>;
@Injectable()
export class UserService {
    private resourceUrl = SERVER_API_URL + 'api/users';

    constructor(private http: HttpClient, private dateUtils: JhiDateUtils,private userExtraInfoService:UserExtraInfoService) { }

    create(user: User): Observable<HttpResponse<User>> {
        const copy = this.convert(user);
        return this.http.post<User>(this.resourceUrl, copy, { observe: 'response' }).map((res: EntityResponseType) => this.convertResponse(res));
    }

    update(user: User): Observable<HttpResponse<User>> {
        const copy = this.convert(user);
        return this.http.put<User>(this.resourceUrl, copy, { observe: 'response' }).map((res: EntityResponseType) => this.convertResponse(res));
    }

    find(login: string): Observable<HttpResponse<User>> {
        return this.http.get<User>(`${this.resourceUrl}/${login}`, { observe: 'response' }).map((res: EntityResponseType) => this.convertResponse(res));
    }

    query(req?: any): Observable<HttpResponse<User[]>> {
        const options = createRequestOption(req);
        return this.http.get<User[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(login: string): Observable<HttpResponse<any>> {
        return this.http.delete(`${this.resourceUrl}/${login}`, { observe: 'response' });
    }

    authorities(): Observable<string[]> {
        return this.http.get<string[]>(SERVER_API_URL + 'api/users/authorities');
    }
    authoritiesAll(): Observable<any[]> {
      return this.http.get<any[]>(SERVER_API_URL + 'api/users/authorities-all');
    }

  private convertResponse(res: EntityResponseType): EntityResponseType {
    const body: User = this.convertItemFromServer(res.body);
    return res.clone({body});
  }
  /**
   * Convert a returned JSON object to User.
   */
  /**
   * Convert a UserExtraInfo to a JSON which can be sent to the server.
   */
  private convert(user: User): User {
    const copy: User = Object.assign({}, user);
    if(copy.userExtraInfo){
      copy.userExtraInfo = this.userExtraInfoService.convert(copy.userExtraInfo);

    }
    return copy;
  }
  private convertItemFromServer(user: User): User {
    const copy: User = Object.assign({}, user);
    if(copy.userExtraInfo){
      copy.userExtraInfo = this.userExtraInfoService.convertItemFromServer(copy.userExtraInfo);
    }
    return copy;
  }


}
