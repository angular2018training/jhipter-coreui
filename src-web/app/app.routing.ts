import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

// Import Containers
import { DefaultLayoutComponent } from './containers';

import { P404Component } from './views/error/404.component';
import { P500Component } from './views/error/500.component';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';
import { adminState } from './admin/admin.route';
import { DashboardModule } from './views/dashboard/dashboard.module';
import { NextlogixAdminModule } from './admin/admin.module';
import { BaseModule } from './views/base/base.module';
import { ButtonsModule } from './views/buttons/buttons.module';
import { ChartJSModule } from './views/chartjs/chartjs.module';
import { IconsModule } from './views/icons/icons.module';
import { NotificationsModule } from './views/notifications/notifications.module';
import { ThemeModule } from './views/theme/theme.module';
import { WidgetsModule } from './views/widgets/widgets.module';
import { errorRoute } from './layouts/error/error.route';
import { NextlogixSetupModule } from './setup/setup.module';
import { setupRouter } from '@angular/router/src/router_module';
import { setupState } from './setup/setup.route';
import { customerManagementState } from './customer-management/customer-management.route';
import { NextlogixAccountModule } from './account/account.module';
import { NextlogixCustomerManagmentModule } from './customer-management/customer-management.module';

export const routes: Routes = [

  {
    path: '404',
    component: P404Component,
    data: {
      title: 'Page 404'
    }
  },
  {
    path: '500',
    component: P500Component,
    data: {
      title: 'Page 500'
    }
  },
  {
    path: 'login',
    component: LoginComponent,
    data: {
      title: 'Login Page'
    }
  },
  {
    path: 'register',
    component: RegisterComponent,
    data: {
      title: 'Register Page'
    }
  },

  {
    path: '',
    component: DefaultLayoutComponent,
    data: {
      title: 'Home'
    },
    children: [
      {
        path: 'base',
        loadChildren: () => BaseModule
      },
      {
        path: 'buttons',
        loadChildren: () => ButtonsModule
      },
      {
        path: 'charts',
        loadChildren: () => ChartJSModule
      },
      {
        path: 'dashboard',
        loadChildren: () => DashboardModule
      },
      {
        path: 'icons',
        loadChildren: () => IconsModule
      },
      {
        path: 'notifications',
        loadChildren: () => NotificationsModule
      },
      {
        path: 'theme',
        loadChildren: () => ThemeModule
      },
      {
        path: 'widgets',
        loadChildren: () => WidgetsModule
      },
      {
        path: 'admin',
        loadChildren: () => NextlogixAdminModule,
        // children:adminState,
        data: {
          breadcrumb: 'Admin'
        }
      },
      {
        path: 'setup',
        // children:setupState,
        loadChildren: () => NextlogixSetupModule,
        data: {
          breadcrumb: 'Setup '
        }
      },
      {
        path: 'customer-management',
        loadChildren: () => NextlogixCustomerManagmentModule,
        data: {
          breadcrumb: 'Customer Management '
        }
      },
      {
        path: 'account',
        loadChildren: () => NextlogixAccountModule,
        data: {
          breadcrumb: 'Account '
        }
      },
      ...errorRoute

    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    enableTracing: false, // <-- debugging purposes o
    useHash: true
  })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
