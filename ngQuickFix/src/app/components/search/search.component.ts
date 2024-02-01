import { ProjectArea } from './../../models/project-area';
import { Trade } from './../../models/trade';
import { SearchService } from './../../services/search.service';
import { Component, OnInit } from '@angular/core';
import { JobPost } from '../../models/job-post';
import { User } from '../../models/user';
import { Provider } from '../../models/provider';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [
    FormsModule,
    CommonModule,
    NgbModule,
    RouterLink
  ],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css'
})
export class SearchComponent implements OnInit {
  searchQuery: string = '';
  searchResults: Provider[] | User[] | JobPost[] | any = [];
  searchType: 'providers' | 'users' | 'jobposts' | '' = '';
  selectedSortOption: string = 'company';
  minBudget: number = 0;
  maxBudget: number = Infinity;
  unfilteredResults?: any[];
  materialsProvidedFilter: string = 'all';
  projectAreas: ProjectArea[] = [];
  trades: Trade [] = [];
  account: boolean = false;


  constructor(
    private searchService: SearchService,
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService
    ) { }

    ngOnInit(): void {
      this.route.queryParams.subscribe(params => {
        console.log('Received query params:', params);
        if (params['query'] && params['type']) {
          this.searchQuery = params['query'];
          this.searchType = params['type'];
          this.search();
        }
      });
    }


  search(): void {
    if (!this.loggedIn()) {
      this.router.navigate(['/login']);
      return;
    }
    if (this.searchQuery) {
      switch (this.searchType) {
        case 'providers':
          this.searchService.searchProviders(this.searchQuery).subscribe(
            data => {
              console.log('Search Results:', data);
              this.searchResults = data;
            },
            error => { console.error(error); }
          );
          break;
        case 'users':
          this.searchService.searchUsers(this.searchQuery).subscribe(
            data => { this.searchResults = data; },
            error => { console.error(error); }
          );
          break;
        case 'jobposts':
          this.searchService.searchJobPosts(this.searchQuery).subscribe(
            data => {
              this.searchResults = data;
              this.storeUnfilteredResults(data);
              this.applyBudgetFilter();
            },
            error => { console.error(error); }
          );
          break;
      }
    }
  }

  sortResults(): void {
    if (this.searchType === 'providers' && this.selectedSortOption === 'company') {
      (this.searchResults as Provider[]).sort((a, b) => a.company.localeCompare(b.company));
    } else if (this.searchType === 'providers' && this.selectedSortOption === 'rate') {
      (this.searchResults as Provider[]).sort((a, b) => a.ratePerHour - b.ratePerHour);
    } else if (this.searchType === 'users' && this.selectedSortOption === 'username') {
      (this.searchResults as User[]).sort((a, b) => a.username.localeCompare(b.username));
    }
  }

  applyBudgetFilter(): void {
    if (this.searchType === 'jobposts' && this.unfilteredResults) {
      this.searchResults = this.unfilteredResults.filter(jobPost => {
        return jobPost.budgetMax >= this.minBudget && jobPost.budgetMax <= this.maxBudget;
      });
    }
  }

  // Call this method after retrieving search results
  storeUnfilteredResults(results: any[]): void {
    this.unfilteredResults = results;
  }

  applyMaterialsProvidedFilter(): void {
    if (this.searchType === 'jobposts' && this.unfilteredResults) {
      if (this.materialsProvidedFilter !== 'all') {
        const filterValue = this.materialsProvidedFilter === 'true';
        this.searchResults = this.unfilteredResults.filter(jobPost =>
          jobPost.materialsProvided === filterValue
        );
      } else {
        this.searchResults = [...this.unfilteredResults];
      }
    }
  }

  loggedIn(){
    this.account = this.authService.checkLogin();
   return this.account;
  }

  viewPost(post: JobPost) {
    this.router.navigateByUrl('singlePost/' + post.id);
  }



}
