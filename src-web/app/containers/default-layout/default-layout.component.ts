import {Component, Input, OnInit} from '@angular/core';
import { navItems } from './../../_nav';
import {LoginService} from "../../shared/login/login.service";
import {ActivatedRouteSnapshot, NavigationEnd, Router} from "@angular/router";
import {Title} from "@angular/platform-browser";
import { ToasterModule, ToasterService, ToasterConfig }  from 'angular2-toaster/angular2-toaster';
import { JhiLanguageHelper } from '../../shared';
import {JhiLanguageService} from "ng-jhipster";
@Component({
  selector: 'app-dashboard',
  templateUrl: './default-layout.component.html'
})
export class DefaultLayoutComponent implements OnInit{
  public navItems = navItems;
  public sidebarMinimized = true;
  private changes: MutationObserver;
  public element: HTMLElement = document.body;
  languages: any[];

  constructor( private loginService: LoginService, private router: Router,private titleService: Title, private jhiLanguageHelper: JhiLanguageHelper,
               private languageService: JhiLanguageService,private toasterService: ToasterService) {
    console.log('languageService'+languageService);
    this.languages = new Array();
    this.changes = new MutationObserver((mutations) => {
      this.sidebarMinimized = document.body.classList.contains('sidebar-minimized')
    });

    this.changes.observe(<Element>this.element, {
      attributes: true
    });
  }
  logout() {
    this.loginService.logout();
    this.router.navigate(['']);
  }
  private getPageTitle(routeSnapshot: ActivatedRouteSnapshot) {
    let title: string = (routeSnapshot.data && routeSnapshot.data['pageTitle']) ? routeSnapshot.data['pageTitle'] : 'nextlogixApp';
    if (routeSnapshot.firstChild) {
      title = this.getPageTitle(routeSnapshot.firstChild) || title;
    }
    return title;
  }

  ngOnInit() {
    this.jhiLanguageHelper.getAll().then((languages) => {
      this.languages = languages;
    });
    this.router.events.subscribe((event) => {
      if (event instanceof NavigationEnd) {
        this.jhiLanguageHelper.updateTitle(this.getPageTitle(this.router.routerState.snapshot.root));
      }
    });
  }
  changeLanguage(languageKey: string) {
    this.languageService.changeLanguage(languageKey);
  }
  getLanguage (){
    return this.languageService.currentLang;
  }
}
