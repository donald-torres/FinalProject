import { HttpClient } from '@angular/common/http';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from './../../environments/environment';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { JobPost } from '../models/job-post';
import { User } from '../models/user';
import { Provider } from '../models/provider';

@Injectable({
  providedIn: 'root',
})
export class SearchService {
  private url = environment.baseUrl + 'api/search';
  constructor(private http: HttpClient, private authService: AuthService) {}

  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  searchProviders(company: string): Observable<Provider[]> {
    return this.http
      .get<Provider[]>(`${this.url}/providers/${company}`, this.getHttpOptions())
      .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () =>
              new Error(
                'SearchService.searchProviders(): error retrieving Providers: ' +
                  err
              )
          );
        })
      );
  }

  searchUsers(username: string): Observable<User[]> {
    return this.http.get<User[]>(`${this.url}/users/${username}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('SearchService.searchUsers(): error retrieving Users: ' + err)
        );
      })
    );
  }

  searchJobPosts(title: string): Observable<JobPost[]> {
    return this.http.get<JobPost[]>(`${this.url}/jobposts/${title}`, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('SearchService.searchJobPosts(): error retrieving JobPosts: ' + err)
        );
      })
    );
  }

}
