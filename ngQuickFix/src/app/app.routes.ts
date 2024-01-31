import { Routes } from '@angular/router';
import { IndexComponent } from './components/index/index.component';
import { RegisterComponent } from './components/register/register.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { AboutComponent } from './components/about/about.component';
import { ContactComponent } from './components/contact/contact.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { SearchComponent } from './components/search/search.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { SinglePostComponent } from './components/single-post/single-post.component';


  export const routes: Routes = [
    { path: '', pathMatch: 'full', redirectTo: 'index' },
    { path: 'index', component: IndexComponent},
    { path: 'about', component: AboutComponent },
    { path: 'contact', component: ContactComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'login', component: LoginComponent },
    { path: 'logout', component: LogoutComponent },
    { path: 'search', component: SearchComponent },
    { path: 'dashboard', component: DashboardComponent },
    { path: 'singlePost/:postId', component: SinglePostComponent },
    { path: '**', component: NotFoundComponent },
  ];


