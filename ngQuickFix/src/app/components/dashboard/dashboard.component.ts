import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { JobPost } from '../../models/job-post';
import { UserService } from '../../services/user.service';
import { User } from '../../models/user';
import { Provider } from '../../models/provider';
import { JobPostService } from '../../services/job-post.service';
import { AuthService } from '../../services/auth.service';
import { SinglePostComponent } from '../single-post/single-post.component';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { Bid } from '../../models/bid';
import { ProviderService } from '../../services/provider.service';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [FormsModule, CommonModule, SinglePostComponent, RouterLink],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent implements OnInit {
  jobPosts: JobPost[] = [];
  loggedInUser: User = new User();
  newPost: JobPost = new JobPost();
  editUser: User | null = null;
  editProvider: Provider | null = null;
  selected: JobPost | null = null;
  bids: Bid[] = [];

  constructor(
    private userService: UserService,
    private jobPostService: JobPostService,
    private authService: AuthService,
    private router: Router,
    private route: ActivatedRoute,
    private providerService: ProviderService
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

  setEditUser() {
    this.editUser = Object.assign({}, this.loggedInUser);
  }

  updateUser(editUser: User) {
    this.userService.update(editUser).subscribe({
      next: (result) => {
        this.getCurrentUser();
        this.editUser = null;
      },
      error: (problem) => {
        console.error('DashboardComponent.updateUser(): error updateing User:');
        console.error(problem);
      },
    });
  }
  updateBid(postId: number, bidId: number, bid: Bid) {
    console.log(bid);
    this.jobPostService.updateBid(postId, bidId, bid).subscribe({
      next: (result) => {},
      error: (problem) => {
        console.error('DashboardComponent.updateBid(): error updateing Bid:');
        console.error(problem);
      },
    });
  }
  toggleAcceptBid(event: any, postId: number, bidId: number) {
    if (event.target.checked) {
      this.jobPostService.acceptBid(postId, bidId).subscribe({
        next: (result) => {},
        error: (problem) => {
          console.error('DashboardComponent.updateBid(): error updateing Bid:');
          console.error(problem);
        },
      });
    } else {
      this.jobPostService.unAcceptBid(postId, bidId).subscribe({
        next: (result) => {},
        error: (problem) => {
          console.error('DashboardComponent.updateBid(): error updateing Bid:');
          console.error(problem);
        },
      });
    }
  }

  viewPost(post: JobPost) {
    this.router.navigateByUrl('singlePost/' + post.id);
  }
  getBids(postId: number) {
    this.jobPostService.indexBids(postId).subscribe({
      next: (data) => {
        this.bids = data;
      },
      error: (problem) => {
        console.error(
          'SinglePostComponent.reload(): error reloading projectAreas:'
        );
        console.error(problem);
      },
    });
  }
  getPost(postId: number) {
    this.jobPostService.show(postId).subscribe({
      next: (data) => {
        this.selected = data;
      },
      error: (problem) => {
        console.error(
          'SinglePostComponent.reload(): error reloading projectAreas:'
        );
        console.error(problem);
      },
    });
  }

  showBids(post: JobPost) {
    this.getBids(post.id);
    console.log(this.bids);
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

    // this.providerService.show(this.loggedInUser.provider[0].id).subscribe({
    //   next: (data) => {
    //     this.editProvider = data;
    //   },
    //   error: (problem) => {
    //     console.error('DashboardComponent.reload(): error reloading jobPosts:');
    //     console.error(problem);
    //   },
    // });
  }
}
