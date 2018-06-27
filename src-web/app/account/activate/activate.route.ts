import { Route } from '@angular/router';

import { ActivateComponent } from './activate.component';
import {UserRouteAccessService} from "../../shared/auth/user-route-access-service";

export const activateRoute: Route = {
    path: 'activate',
    component: ActivateComponent,
    data: {
        authorities: [],
        pageTitle: 'activate.title'
    },
    canActivate: [UserRouteAccessService]
};
