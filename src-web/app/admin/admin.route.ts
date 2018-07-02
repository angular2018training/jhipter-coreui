import { Routes } from '@angular/router';

import {
  auditsRoute,
  configurationRoute,
  docsRoute,
  healthRoute,
  logsRoute,
  metricsRoute,
  userMgmtRoute
} from './';

import { UserRouteAccessService } from '../shared';
import { elasticsearchReindexRoute } from './elasticsearch-reindex/elasticsearch-reindex.route';
import { userGroupRoute } from './user-group/user-group.route';

const ADMIN_ROUTES = [
  auditsRoute,
  configurationRoute,
  docsRoute,
  healthRoute,
  logsRoute,
  ...userMgmtRoute,
  metricsRoute,
  elasticsearchReindexRoute,
  ...userGroupRoute,
  { path: '', redirectTo: '/admin/user-management', pathMatch: 'full' }
];

export const adminState: Routes = [{
  path: '',
  data: {
    authorities: ['ROLE_ADMIN']
  },
  canActivate: [UserRouteAccessService],
  children: ADMIN_ROUTES
}];
