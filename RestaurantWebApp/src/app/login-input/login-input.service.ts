import { Injectable } from '@angular/core';
import { Login } from 'src/models/Login';

import { HttpClient } from '@angular/common/http';
import {Observable} from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class InputService {
    private dbUrl ='http://localhost:8080/api/v1/staff'
    
    constructor(private httpClient: HttpClient) {}
    
    public getLogin(username: string, password: string): Observable<Login[]> {
        const queryURL  = this.dbUrl.concat(`/${username}/${password}`);
         return this.httpClient.get<Login[]>(queryURL)
          .pipe(
            map(response => response)
          );
    }
    
}
