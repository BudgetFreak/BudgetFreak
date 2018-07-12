import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../model/model-interfaces";
import {Observable} from 'rxjs';
import {ErrorHandlerService} from "../error-handler.service";


const BASE_URL = 'http://localhost:8080/users/';

@Injectable()
export class UserService {

  constructor(private http: HttpClient) {
  }

  findAll(): Observable<User[]> {
    return this.http.get<User[]>(BASE_URL);
  }

  create(user: User): Observable<User> {
    return this.http.post(BASE_URL, JSON.stringify(user));
  }

}
