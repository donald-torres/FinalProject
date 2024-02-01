import { User } from './../../models/user';
import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { IndexComponent } from '../index/index.component';
import { LoginComponent } from '../login/login.component';
import { LogoutComponent } from '../logout/logout.component';
import { RegisterComponent } from '../register/register.component';
import { AuthService } from '../../services/auth.service';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { SearchService } from '../../services/search.service';


@Component({
  selector: 'app-navigation',
  standalone: true,
  imports: [
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    IndexComponent,
    CommonModule,
    FormsModule,
    RouterLink,
    NgbModule,
    DashboardComponent,
  ],
  templateUrl: './navigation.component.html',
  styleUrl: './navigation.component.css',
})
export class NavigationComponent implements OnInit {

  account: boolean = false;

  public isCollapsed: boolean = false;

  searchQuery: string = '';
  searchType: 'providers' | 'users' | 'jobposts' | '' = '';
  loggedInUser: User = new User();

  constructor(
    private authService: AuthService,
    private router: Router
    ){}

  ngOnInit(): void {
    this.getCurrentUser();
  }

  loggedIn(){
    this.account = this.authService.checkLogin();
   return this.account;
  }

  // Method to perform the search
  performSearch(): void {
    console.log('Search Query:', this.searchQuery, 'Search Type:', this.searchType);
    if (this.searchQuery) {
      this.router.navigate(['/search'], { queryParams: { query: this.searchQuery, type: this.searchType } });
    }
  }

  getCurrentUser() {
    this.authService.getLoggedInUser().subscribe({
      next: (data) => {
        this.loggedInUser = data;
      },
      error: (problem) => {
        console.error('UserComponent.reload(): error reloading user:');
        console.error(problem);
      },
    });
}


}
