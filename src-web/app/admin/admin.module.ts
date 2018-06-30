import {NgModule, CUSTOM_ELEMENTS_SCHEMA} from '@angular/core';
import {RouterModule} from '@angular/router';

import {NextlogixSharedModule} from '../shared';
/* jhipster-needle-add-admin-module-import - JHipster will add admin modules imports here */

import {
  adminState,
  AuditsComponent,
  UserMgmtComponent,
  UserDeleteDialogComponent,
  UserMgmtDetailComponent,
  UserMgmtEditComponent,
  UserMgmtDeleteDialogComponent,
  LogsComponent,
  JhiMetricsMonitoringModalComponent,
  JhiMetricsMonitoringComponent,
  JhiHealthModalComponent,
  JhiHealthCheckComponent,
  JhiConfigurationComponent,
  JhiDocsComponent,
  AuditsService,
  JhiConfigurationService,
  JhiHealthService,
  JhiMetricsService,
  LogsService,
  UserResolvePagingParams,
  UserResolve,
  UserModalService
} from './';
import {ElasticsearchReindexComponent} from './elasticsearch-reindex/elasticsearch-reindex.component';
import {ElasticsearchReindexModalComponent} from './elasticsearch-reindex/elasticsearch-reindex-modal.component';
import {ElasticsearchReindexService} from './elasticsearch-reindex/elasticsearch-reindex.service';
import {NextlogixUserGroupModule} from './user-group/user-group.module';
import {JhiLanguageService} from 'ng-jhipster';
import {JhiLanguageHelper} from '../shared/language/language.helper';
import {UserMgmtUpdateComponent} from './user-management/user-management-update.component';

@NgModule({
  imports: [
    NextlogixSharedModule,
    NextlogixUserGroupModule,
    RouterModule.forChild(adminState)
    /* jhipster-needle-add-admin-module - JHipster will add admin modules here */
  ],
  declarations: [
    AuditsComponent,
    UserMgmtComponent,
    UserDeleteDialogComponent,
    UserMgmtDetailComponent,
    UserMgmtEditComponent,
    UserMgmtUpdateComponent,
    UserMgmtDeleteDialogComponent,
    LogsComponent,
    JhiConfigurationComponent,
    JhiHealthCheckComponent,
    JhiHealthModalComponent,
    JhiDocsComponent,
    JhiMetricsMonitoringComponent,
    JhiMetricsMonitoringModalComponent,
    ElasticsearchReindexComponent,
    ElasticsearchReindexModalComponent
  ],
  entryComponents: [
    UserMgmtEditComponent,
    UserMgmtDeleteDialogComponent,
    JhiHealthModalComponent,
    JhiMetricsMonitoringModalComponent,
    ElasticsearchReindexComponent,
    ElasticsearchReindexModalComponent
  ],
  providers: [
    AuditsService,
    JhiConfigurationService,
    JhiHealthService,
    JhiMetricsService,
    LogsService,
    UserResolvePagingParams,
    UserResolve,
    UserModalService,
    ElasticsearchReindexService
  ],
  exports: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NextlogixAdminModule {
  constructor(private languageService: JhiLanguageService, private languageHelper: JhiLanguageHelper) {
    this.languageHelper.language.subscribe((languageKey: string) => {
      if (languageKey !== undefined) {
        this.languageService.changeLanguage(languageKey);
      }
    });
  }
}
