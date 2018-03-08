import {Injectable} from '@angular/core';
import {Http, Response} from "@angular/http";
import {Observable} from "rxjs/Observable";
import {User} from "./model/model-interfaces";

const BASE_URL = 'http://localhost:3000/api/users/';

@Injectable()
export class UserService {

  constructor(private http: Http) {
  }

  findAll(): Observable<User[]> {
    return this.http.get(BASE_URL).map(response => response.json());
  }

  create(user: User): Observable<User> {
    return this.http
      .post(BASE_URL, JSON.stringify(user))
      .map((response: Response) => response.json()._embedded.userResources)
      .catch((err: any) => Observable.throw(err.json().error || 'Server error'));
  }
}
