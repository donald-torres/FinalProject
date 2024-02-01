import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { Observable, catchError, throwError } from 'rxjs';
import { environment } from '../../environments/environment';
import { User } from '../models/user';
import { Provider } from '../models/provider';

@Injectable({
  providedIn: 'root',
})
export class ProviderService {
  private url = environment.baseUrl + 'api/providers';

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

  show(id: number): Observable<Provider> {
    return this.http
    .get<Provider>(this.url + '/' + id, this.getHttpOptions())
    .pipe(
        catchError((err: any) => {
          console.log(err);
          return throwError(
            () => new Error('UserService.show(): error retrieving User: ' + err)
          );
        })
      );
  }

  update(provider: Provider): Observable<Provider> {
    return this.http.put<Provider>(
      this.url + '/' + provider.id,
      provider,
      this.getHttpOptions()
    );
  }
}
