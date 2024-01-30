import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { JobPost } from '../../models/job-post';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { JobPostService } from '../../services/job-post.service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent implements OnInit {
  jobPosts: JobPost[] = [];
  loggedInUser: User = new User();
  newPost: JobPost = new JobPost();

  constructor(
    private userService: UserService,
    private jobPostService: JobPostService,
    private authService: AuthService
  ) {}

  getPostCount(): number {
    return this.jobPosts.length;
  }

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
  addPost(newPost: JobPost) {
    this.jobPostService.create(newPost).subscribe({
      next: (result) => {
        this.reload();
        this.newPost = new JobPost();
      },
      error: (problem) => {
        console.error('DashboardComponent.addPost(): error creating Post:');
        console.error(problem);
      },
    });
  }

  reload() {
    this.jobPostService.index().subscribe({
      next: (data) => {
        this.jobPosts = data;
      },
      error: (problem) => {
        console.error('DashboardComponent.reload(): error reloading jobPosts:');
        console.error(problem);
      },
    });
  }
}
