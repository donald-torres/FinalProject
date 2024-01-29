import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { JobPost } from '../../models/job-post';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { JobPostService } from '../../services/job-post.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [FormsModule, CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{
  jobPosts: JobPost[] = [];
  user: User = new User();
  
  constructor(private userService: UserService, private jobPostService: JobPostService){}

  getPostCount(): number {
    return this.jobPosts.length;
  }

  ngOnInit(): void {
   this.reload();
  }


  reload(){
    this.jobPostService.index().subscribe(
      {
        next: (data) => {
          this.jobPosts = data;
        },
        error: (problem) => {
          console.error('JobPostComponent.reload(): error reloading jobPosts:');
          console.error(problem);
        }
      }
    );
  }
}
