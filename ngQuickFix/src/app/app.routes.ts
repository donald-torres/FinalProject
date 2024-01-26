import { Routes } from '@angular/router';
import { IndexComponent } from './components/index/index.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';


  export const routes: Routes = [
    { path: '', pathMatch: 'full', redirectTo: 'index' },
    { path: 'index', component: IndexComponent},
    // { path: 'about', component: AboutComponent },
    // { path: 'contact', component: ContactComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'login', component: LoginComponent },
    { path: 'logout', component: LogoutComponent }
    // { path: '**', component: NotFoundComponent }
  ];


