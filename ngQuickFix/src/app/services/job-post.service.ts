import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { JobPost } from '../models/job-post';

@Injectable({
  providedIn: 'root'
})
export class JobPostService {
  private url = environment.baseUrl + 'api/jobPosts';


  constructor(private http: HttpClient, private authService: AuthService) { }




  getHttpOptions() {
    let options = {
      headers: {
        Authorization: 'Basic ' + this.authService.getCredentials(),
        'X-Requested-With': 'XMLHttpRequest',
      },
    };
    return options;
  }

  
  index(): Observable<JobPost[]> {
    return this.http.get<JobPost[]>(this.url, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('JobPostService.index(): error retrieving JobPosts: ' + err)
          );
        })
        );
      }
      create(newPost: JobPost): Observable<JobPost> {
        return this.http.post<JobPost>(this.url, newPost, this.getHttpOptions()).pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              () => new Error('JobPostService.create(): error creating Post: ' + err)
            );
          })
        );
      }
}
