import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
    import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from '../../shared';
import { Observable } from 'rxjs';
import { FileUpload } from './file-upload.model';
import { FileUploadService } from './file-upload.service';
import { FileUploadComponent } from './file-upload.component';
import { FileUploadDetailComponent } from './file-upload-detail.component';
import { FileUploadUpdateComponent } from './file-upload-update.component';
    import { FileUploadDeletePopupComponent } from './file-upload-delete-dialog.component';

@Injectable({providedIn: 'root'})
export class FileUploadResolve implements Resolve<FileUpload> {

        constructor(private service: FileUploadService) {
}

resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
        return this.service.find(id).map((fileUpload: HttpResponse<FileUpload>) => fileUpload.body);
    }
    return Observable.of(new FileUpload());
}
}


export const fileUploadRoute: Routes = [
    {
        path: 'file-upload',
        component: FileUploadComponent,
resolve: {
    'pagingParams': JhiResolvePagingParams
},
data: {
    authorities: ['ROLE_USER'],
    defaultSort: 'id,asc',
    pageTitle: 'nextlogixApp.fileUpload.home.title',
    title : 'nextlogixApp.fileUpload.home.title'
},
canActivate: [UserRouteAccessService]
}, {
    path: 'file-upload/:id/view',
        component: FileUploadDetailComponent,
        resolve: {
        fileUpload: FileUploadResolve
    },
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.fileUpload.home.title',
        title: 'nextlogixApp.fileUpload.home.title',
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'file-upload/new',
        component: FileUploadUpdateComponent,
    resolve: {
    fileUpload: FileUploadResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.fileUpload.home.title',
        title: 'nextlogixApp.fileUpload.home.title'
    },
    canActivate: [UserRouteAccessService]
},
{
    path: 'file-upload/:id/edit',
        component: FileUploadUpdateComponent,
    resolve: {
    fileUpload: FileUploadResolve
},
    data: {
        authorities: ['ROLE_USER'],
            pageTitle: 'nextlogixApp.fileUpload.home.title',
        title: 'nextlogixApp.fileUpload.home.title'
    },
    canActivate: [UserRouteAccessService]
},
];

export const fileUploadPopupRoute: Routes = [
    {
        path: 'file-upload/:id/delete',
        component: FileUploadDeletePopupComponent,
        resolve: {
        fileUpload: FileUploadResolve
},
data: {
    authorities: ['ROLE_USER'],
        pageTitle: 'nextlogixApp.fileUpload.home.title',
    title: 'nextlogixApp.fileUpload.home.title'
},
canActivate: [UserRouteAccessService],
    outlet: 'popup'
}
];
