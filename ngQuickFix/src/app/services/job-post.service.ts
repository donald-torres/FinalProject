import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../environments/environment.development';
import { JobPost } from '../models/job-post';
import { Trade } from '../models/trade';
import { ProjectArea } from '../models/project-area';
import { Specialty } from '../models/specialty';

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
  show(postId: number): Observable<JobPost> {
    return this.http.get<JobPost>(this.url +'/'+ postId, this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('JobPostService.show(): error retrieving JobPost: ' + err)
          );
        })
        );
      }


  indexTrades(): Observable<Trade[]> {
    return this.http.get<Trade[]>(environment.baseUrl +'api/trades' , this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('JobPostService.indexTrades(): error retrieving Trades: ' + err)
          );
        })
        );
      }
  indexAreas(): Observable<ProjectArea[]> {
    return this.http.get<ProjectArea[]>(environment.baseUrl +'api/projectAreas', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('JobPostService.indexAreas(): error retrieving ProjectAreas: ' + err)
          );
        })
        );
      }
  indexSpecs(): Observable<Specialty[]> {
    return this.http.get<Specialty[]>(environment.baseUrl +'api/specialties', this.getHttpOptions()).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError(
          () => new Error('JobPostService.indexSpecs(): error retrieving Specialties: ' + err)
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
      update(post: JobPost): Observable<JobPost> {
        return this.http.put<JobPost>(this.url +'/'+ post.id, post, this.getHttpOptions());
      }

      destroy(id: number): Observable<void> {
        return this.http.delete<void>(this.url +'/'+ id, this.getHttpOptions());
      }

}
