import { Injectable } from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes, CanActivate } from '@angular/router';

import {JhiPaginationUtil, JhiResolvePagingParams} from 'ng-jhipster';

import { UserMgmtComponent } from './user-management.component';
import { UserMgmtDetailComponent } from './user-management-detail.component'

import { Principal } from '../../shared';
import {UserMgmtUpdateComponent} from './user-management-update.component';
import {UserService} from '../../shared/user/user.service';
import {User} from '../../shared/user/user.model';

@Injectable({ providedIn: 'root' })
export class UserResolve implements CanActivate {
  constructor(private principal: Principal) {}

  canActivate() {
    return this.principal.identity().then(account => this.principal.hasAnyAuthority(['ROLE_ADMIN']));
  }
}

@Injectable({ providedIn: 'root' })
export class UserMgmtResolve implements Resolve<any> {
  constructor(private service: UserService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['login'] ? route.params['login'] : null;
    if (id) {
      return this.service.find(id);
    }
    return new User();
  }
}

@Injectable()
export class UserResolvePagingParams implements Resolve<any> {

  constructor(private paginationUtil: JhiPaginationUtil) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const page = route.queryParams['page'] ? route.queryParams['page'] : '1';
    const sort = route.queryParams['sort'] ? route.queryParams['sort'] : 'id,asc';
    return {
      page: this.paginationUtil.parsePage(page),
      predicate: this.paginationUtil.parsePredicate(sort),
      ascending: this.paginationUtil.parseAscending(sort)
    };
  }
}


export const userMgmtRoute: Routes = [
  {
    path: 'user-management',
    component: UserMgmtComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      pageTitle: 'userManagement.home.title',
      defaultSort: 'id,asc'
    }
  },
  {
    path: 'user-management/:login/view',
    component: UserMgmtDetailComponent,
    resolve: {
      user: UserMgmtResolve
    },
    data: {
      pageTitle: 'userManagement.home.title'
    }
  },
  {
    path: 'user-management/new',
    component: UserMgmtUpdateComponent,
    resolve: {
      user: UserMgmtResolve
    }
  },
  {
    path: 'user-management/:login/edit',
    component: UserMgmtUpdateComponent,
    resolve: {
      user: UserMgmtResolve
    }
  }
];
