import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { JobPostService } from '../../services/job-post.service';
import { ProviderService } from '../../services/provider.service';
import { UserService } from '../../services/user.service';
import { JobPost } from '../../models/job-post';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { User } from '../../models/user';

@Component({
  selector: 'app-job-posts',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './job-posts.component.html',
  styleUrl: './job-posts.component.css'
})
export class JobPostsComponent implements OnInit{
  jobPosts: JobPost[] = [];
  loggedInUser: User = new User();

  constructor(
    private userService: UserService,
    private jobPostService: JobPostService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private providerService: ProviderService
  ) {}
  ngOnInit(): void {
    this.reload();
    this.getCurrentUser();
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

  reload() {
    this.jobPostService.indexActive().subscribe({
      next: (data) => {
        this.jobPosts = data;
        console.log(this.jobPosts);
        
      },
      error: (problem) => {
        console.error('DashboardComponent.reload(): error reloading jobPosts:');
        console.error(problem);
      },
    });
  }
}
