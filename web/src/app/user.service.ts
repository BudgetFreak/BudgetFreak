import {Injectable} from '@angular/core';
import {Headers, Http, Response} from "@angular/http";
import {User} from "./model/model-interfaces";
import {Observable} from 'rxjs/Observable';

const BASE_URL = 'http://localhost:8080/users';

@Injectable()
export class UserService {

  private headers = new Headers([{'Content-Type': 'application/json'}, {'Accept': 'application/json'}]);

  constructor(private http: Http) {
  }

  findAll(): Observable<User[]> {
    return this.http.get(BASE_URL).map((response: Response) => response.json()._embedded.userResources)
      .catch(this.handleError)
  }

  create(user: User): Observable<User> {
    console.log(JSON.stringify(user));

    return this.http
      .post(BASE_URL, JSON.stringify(user), {headers: this.headers})
      .map((response: Response) => response.json())
      .catch(this.handleError);
  }

  private handleError(error: any) {
    const errMsg = ((error.body || {}).message) ? error.message :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';
    console.error(errMsg);
    console.error(error);
    return Observable.throw(errMsg);
  }
}
