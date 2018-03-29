import {Injectable} from '@angular/core';
import {Headers, Http, Response} from "@angular/http";
import {Observable} from 'rxjs/Rx'
import {User} from "./model/model-interfaces";

const BASE_URL = 'http://localhost:8080/users';

@Injectable()
export class UserService {

  constructor(private http: Http) {
  }

  private headers = new Headers({'Content-Type': 'application/json'});

  findAll(): Observable<User[]> {
    return this.http.get(BASE_URL).map((response: Response) => response.json()._embedded.userResources)
      .catch((err: any) => Observable.throw(err.json().error || 'Server error'))
  }

  create(user: User): Observable<User> {
    console.log(JSON.stringify(user));

    return this.http
      .post(BASE_URL, JSON.stringify(user), {headers: this.headers})
      .map((response: Response) => response.json())
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error);
    return Promise.reject(error.message || error);
  }

}
