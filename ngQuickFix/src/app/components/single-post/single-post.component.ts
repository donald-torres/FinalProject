import { CommonModule } from '@angular/common';
import { Component, Input, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { JobPostService } from '../../services/job-post.service';
import { JobPost } from '../../models/job-post';
import { ProjectArea } from '../../models/project-area';
import { Trade } from '../../models/trade';
import { Specialty } from '../../models/specialty';
import { ActivatedRoute, ParamMap, Router } from '@angular/router';
import { Bid } from '../../models/bid';
import { NgbCollapseModule } from '@ng-bootstrap/ng-bootstrap';
import { User } from '../../models/user';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-single-post',
  standalone: true,
  imports: [FormsModule, CommonModule, NgbCollapseModule],
  templateUrl: './single-post.component.html',
  styleUrl: './single-post.component.css',
})
export class SinglePostComponent implements OnInit {
  editPost: JobPost = new JobPost();
  @Input() selected: JobPost | null = null;
  trades: Trade[] = [];
  projectAreas: ProjectArea[] = [];
  specialties: Specialty[] = [];
  bids: Bid[] = [];
  isCollapsed = true;
  loggedInUser: User = new User();

  constructor(
    private jobPostService: JobPostService,
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService
  ) {}
  ngOnInit(): void {
    this.route.paramMap.subscribe({
      next: (params: ParamMap) => {
        let postIdParam = params.get('postId');
        if (postIdParam) {
          let postId = parseInt(postIdParam); // param values are always strings
          if (isNaN(postId)) {
            // this.router.navigateByUrl("invalidPokemonId"); // Undefined path will match wildcard
          } else {
            this.getPost(postId);
          }
        }
      },
    });
    this.reload();
    this.getCurrentUser()
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
  setEditPost() {
    this.editPost = Object.assign({}, this.selected);
  }
  getPost(postId: number) {
    this.jobPostService.show(postId).subscribe({
      next: (result) => {
        this.editPost = result;
        this.getBids(postId);
      },
      error: (problem) => {
        console.error('SinglePostComponent.getPost(): error retreiving Post:');
        console.error(problem);
      },
    });
  }

  updatePost(editPost: JobPost) {
    this.jobPostService.update(editPost).subscribe({
      next: (result) => {
        this.editPost = result;
        this.router.navigateByUrl('dashboard');
      },
      error: (problem) => {
        console.error('SinglePostComponent.updatePost(): error updating Post:');
        console.error(problem);
      },
    });
  }

  deletePost(postId: number) {
    this.jobPostService.destroy(postId).subscribe({
      next: (result) => {
        this.router.navigateByUrl('dashboard');
      },
      error: (problem) => {
        console.error('SinglePostComponent.deletePost(): error deleting Post:');
        console.error(problem);
      },
    });
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

  postHasArea(areaId: number) {
    return this.editPost.projectAreas?.some((area) => {
      return area.id === areaId;
    });
  }

  toggleArea(event: any, areaId: number) {
    if (event.target.checked) {
      this.jobPostService.createArea(this.editPost.id, areaId).subscribe({
        next: () => {},
        error: (problem) => {
          console.log(problem);
        },
      });
    } else {
      this.jobPostService.deleteArea(this.editPost.id, areaId).subscribe({
        next: () => {},
        error: (problem) => {
          console.log(problem);
        },
      });
    }
  }
  postHasTrade(tradeId: number) {
    return this.editPost.trades?.some((trade) => {
      return trade.id === tradeId;
    });
  }

  toggleTrade(event: any, tradeId: number) {
    if (event.target.checked) {
      this.jobPostService.createTrade(this.editPost.id, tradeId).subscribe({
        next: () => {},
        error: (problem) => {
          console.log(problem);
        },
      });
    } else {
      this.jobPostService.deleteTrade(this.editPost.id, tradeId).subscribe({
        next: () => {},
        error: (problem) => {
          console.log(problem);
        },
      });
    }
  }
  postHasSpecailty(specId: number) {
    return this.editPost.specialties?.some((spec) => {
      return spec.id === specId;
    });
  }

  toggleSpecialty(event: any, specId: number) {
    if (event.target.checked) {
      this.jobPostService.createSpecialty(this.editPost.id, specId).subscribe({
        next: () => {},
        error: (problem) => {
          console.log(problem);
        },
      });
    } else {
      this.jobPostService.deleteSpecialty(this.editPost.id, specId).subscribe({
        next: () => {},
        error: (problem) => {
          console.log(problem);
        },
      });
    }
  }

  bidOnPost(amount: number){
    //FIXME
  }

  reload() {
    this.jobPostService.indexTrades().subscribe({
      next: (data) => {
        this.trades = data;
      },
      error: (problem) => {
        console.error('SinglePostComponent.reload(): error reloading trades:');
        console.error(problem);
      },
    });
    this.jobPostService.indexSpecs().subscribe({
      next: (data) => {
        this.specialties = data;
      },
      error: (problem) => {
        console.error(
          'SinglePostComponent.reload(): error reloading specialties:'
        );
        console.error(problem);
      },
    });
    this.jobPostService.indexAreas().subscribe({
      next: (data) => {
        this.projectAreas = data;
      },
      error: (problem) => {
        console.error(
          'SinglePostComponent.reload(): error reloading projectAreas:'
        );
        console.error(problem);
      },
    });
  }
}
