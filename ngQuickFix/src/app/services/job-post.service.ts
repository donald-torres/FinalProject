import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { JobPost } from '../models/job-post';
import { Trade } from '../models/trade';
import { ProjectArea } from '../models/project-area';
import { Specialty } from '../models/specialty';
import { Bid } from '../models/bid';

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
  indexBids(postId: number): Observable<Bid[]> {
    return this.http.get<Bid[]>(this.url +'/'+ postId + '/bids', this.getHttpOptions()).pipe(
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
      createArea(postId: number, areaId: number): Observable<void> {
        return this.http.post<void>(`${this.url}/${postId}/projectAreas/${areaId}`, null, this.getHttpOptions()).pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              () => new Error('JobPostService.create(): error creating Post: ' + err)
            );
          })
        );
      }
      deleteArea(postId: number, areaId: number): Observable<void> {
        return this.http.delete<void>(`${this.url}/${postId}/projectAreas/${areaId}`, this.getHttpOptions()).pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              () => new Error('JobPostService.create(): error creating Post: ' + err)
            );
          })
        );
      }
      createSpecialty(postId: number, specId: number): Observable<void> {
        return this.http.post<void>(`${this.url}/${postId}/specialties/${specId}`, null, this.getHttpOptions()).pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              () => new Error('JobPostService.create(): error creating Post: ' + err)
            );
          })
        );
      }
      deleteSpecialty(postId: number, specId: number): Observable<void> {
        return this.http.delete<void>(`${this.url}/${postId}/specialties/${specId}`, this.getHttpOptions()).pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              () => new Error('JobPostService.create(): error creating Post: ' + err)
            );
          })
        );
      }
      createTrade(postId: number, tradeId: number): Observable<void> {
        return this.http.post<void>(`${this.url}/${postId}/trades/${tradeId}`, null, this.getHttpOptions()).pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              () => new Error('JobPostService.create(): error creating Post: ' + err)
            );
          })
        );
      }
      deleteTrade(postId: number, tradeId: number): Observable<void> {
        return this.http.delete<void>(`${this.url}/${postId}/trades/${tradeId}`, this.getHttpOptions()).pipe(
          catchError((err: any) => {
            console.log(err);
            return throwError(
              () => new Error('JobPostService.create(): error creating Post: ' + err)
            );
          })
        );
      }
      update(updatingPost: JobPost): Observable<JobPost> {
        let post = Object.assign({}, updatingPost);
        delete post.projectAreas;
        delete post.trades;
        delete post.specialties;
        return this.http.put<JobPost>(this.url +'/'+ post.id, post, this.getHttpOptions());
      }

      destroy(id: number): Observable<void> {
        return this.http.delete<void>(this.url +'/'+ id, this.getHttpOptions());
      }

}
