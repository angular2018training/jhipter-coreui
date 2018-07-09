import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { Subject } from 'rxjs/Subject';
import { AccountService } from './account.service';
import {LocalStorageService, SessionStorageService} from "ngx-webstorage";

@Injectable({ providedIn: 'root' })
export class Principal {
    private userIdentity: any;
    private authenticated = false;
    private authenticationState = new Subject<any>();

    constructor(
        private account: AccountService,
        private localStorage: LocalStorageService,
        private sessionStorage: SessionStorageService
    ) {}

    authenticate(identity) {
        this.userIdentity = identity;
        this.authenticated = identity !== null;
        const lang = this.localStorage.retrieve('lang') || this.sessionStorage.retrieve('lang');
        const postOffice = this.localStorage.retrieve('postOffice') || this.sessionStorage.retrieve('postOffice');
        if(identity !== null && !lang){
          this.localStorage.store('lang',this.userIdentity.langKey );
          this.sessionStorage.store('lang',this.userIdentity.langKey );
        }
        if(!identity){
          this.localStorage.clear('lang');
          this.sessionStorage.clear('lang');
        }
        if(identity !== null && !postOffice){
          this.localStorage.store('postOffice',this.userIdentity.offices!=null&&this.userIdentity.offices.length > 0?this.userIdentity.offices[0].id:null );
          this.sessionStorage.store('postOffice',this.userIdentity.offices!=null&&this.userIdentity.offices.length > 0?this.userIdentity.offices[0].id:null );
        }
        if(!identity){
          this.localStorage.clear('postOffice');
          this.sessionStorage.clear('postOffice');
        }
        this.authenticationState.next(this.userIdentity);

    }

    hasAnyAuthority(authorities: string[]): Promise<boolean> {
        return Promise.resolve(this.hasAnyAuthorityDirect(authorities));
    }

    hasAnyAuthorityDirect(authorities: string[]): boolean {
        if (!this.authenticated || !this.userIdentity || !this.userIdentity.authorities) {
            return false;
        }

        for (let i = 0; i < authorities.length; i++) {
            if (this.userIdentity.authorities.includes(authorities[i])) {
                return true;
            }
        }

        return false;
    }

    hasAuthority(authority: string): Promise<boolean> {
        if (!this.authenticated) {
           return Promise.resolve(false);
        }

        return this.identity().then((id) => {
            return Promise.resolve(id.authorities && id.authorities.includes(authority));
        }, () => {
            return Promise.resolve(false);
        });
    }

    identity(force?: boolean): Promise<any> {
        if (force === true) {
            this.userIdentity = undefined;
        }

        // check and see if we have retrieved the userIdentity data from the server.
        // if we have, reuse it by immediately resolving
        if (this.userIdentity) {
            return Promise.resolve(this.userIdentity);
        }

        // retrieve the userIdentity data from the server, update the identity object, and then resolve.
        const postOffice = this.localStorage.retrieve('postOffice') || this.sessionStorage.retrieve('postOffice');
        return this.account.get({postOfficeId:postOffice}).toPromise().then((response) => {
            const account = response.body;
            if (account) {
                this.userIdentity = account;
                const lang = this.localStorage.retrieve('lang') || this.sessionStorage.retrieve('lang');
                if( this.userIdentity !== null && !lang){
                  this.localStorage.store('lang',this.userIdentity.langKey );
                  this.sessionStorage.store('lang',this.userIdentity.langKey );
                }
                if(this.userIdentity !== null && !postOffice && !!force){
                    this.localStorage.store('postOffice',this.userIdentity.offices!=null&&this.userIdentity.offices.length > 0?this.userIdentity.offices[0].id:null);
                    this.sessionStorage.store('postOffice',this.userIdentity.offices!=null&&this.userIdentity.offices.length > 0?this.userIdentity.offices[0].id:null);
                  }
                if(!this.userIdentity && !!force){
                this.localStorage.clear('postOffice');
                this.sessionStorage.clear('postOffice');
                }
                this.authenticated = true;
            } else {
                this.userIdentity = null;
                this.authenticated = false;
            }
            this.authenticationState.next(this.userIdentity);
            return this.userIdentity;
        }).catch((err) => {
            this.userIdentity = null;
            this.authenticated = false;
            this.authenticationState.next(this.userIdentity);
            return null;
        });
    }

    isAuthenticated(): boolean {
        return this.authenticated;
    }

    isIdentityResolved(): boolean {
        return this.userIdentity !== undefined;
    }

    public get currentOffice(){
      const postOffice = this.localStorage.retrieve('postOffice') || this.sessionStorage.retrieve('postOffice');
      return postOffice;
    }
    public set currentOffice(postOfficeId){
      this.localStorage.store('postOffice',postOfficeId );
      this.sessionStorage.store('postOffice',postOfficeId );
      this.identity(true);
    }

    getAuthenticationState(): Observable<any> {
        return this.authenticationState.asObservable();
    }

    getImageUrl(): String {
        return this.isIdentityResolved() ? this.userIdentity.imageUrl : null;
    }
}
