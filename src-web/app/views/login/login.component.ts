import {Component, ElementRef, Renderer} from '@angular/core';
import {JhiEventManager} from "ng-jhipster";
import {LoginService} from "../../shared/login/login.service";
import {StateStorageService} from "../../shared/auth/state-storage.service";
import {Router} from "@angular/router";
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-dashboard',
  templateUrl: 'login.component.html'
})
export class LoginComponent {
  authenticationError: boolean;
  password: string;
  rememberMe: boolean;
  username: string;
  credentials: any;
  constructor(
    private eventManager: JhiEventManager,
    private loginService: LoginService,
    private stateStorageService: StateStorageService,
    private elementRef: ElementRef,
    private renderer: Renderer,
    private router: Router
  ) {
    this.credentials = {};
  }

  ngAfterViewInit() {
    this.renderer.invokeElementMethod(this.elementRef.nativeElement.querySelector('#username'), 'focus', []);
  }
  login() {
    console.log('login')
    this.loginService.login({
      username: this.username,
      password: this.password,
      rememberMe: this.rememberMe
    }).then(() => {
      this.authenticationError = false;
      console.log('login sucess')
      if (this.router.url === '/register' || (/^\/activate\//.test(this.router.url)) ||
        (/^\/reset\//.test(this.router.url))) {
        this.router.navigate(['']);
      }

      this.eventManager.broadcast({
        name: 'authenticationSuccess',
        content: 'Sending Authentication Success'
      });

      // // previousState was set in the authExpiredInterceptor before being redirected to login modal.
      // // since login is succesful, go to stored previousState and clear previousState
      const redirect = this.stateStorageService.getUrl();
      if (redirect) {
        this.stateStorageService.storeUrl(null);
        this.router.navigate([redirect]);
      }else {
        this.router.navigate(['/dashboard']);
      }
    }).catch((e) => {
      console.log('login error'+e)
      this.authenticationError = true;
    });
  }
}
