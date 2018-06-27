import { Route } from '@angular/router';

import { SettingsComponent } from './settings.component';
import {UserRouteAccessService} from "../../shared/auth/user-route-access-service";

export const settingsRoute: Route = {
    path: 'settings',
    component: SettingsComponent,
    data: {
        authorities: ['ROLE_USER'],
        pageTitle: 'global.menu.account.settings'
    },
    canActivate: [UserRouteAccessService]
};
