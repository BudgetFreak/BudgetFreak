import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {Observable} from "rxjs/Observable";
import {User} from "../model/model-interfaces";

@Injectable()
export class UserService {

  private headers = new Headers({'Content-Type': 'application/json'});

  private apiUrl = 'http://localhost:8080/users';

  constructor(private http: Http) {

  }

  findAll(): Observable<User[]> {
    return this.http
      .get(this.apiUrl)
      .map((response: Response) => response.json()._embedded.userResources)
      .catch((err: any) => Observable.throw(err.json().error || 'Server error'));
  }

  findById(id: Number): Observable<User> {
    //TODO rukl@all implement
    return null;
  }

  create(user: User): Observable<User> {
    return this.http
      .post(this.apiUrl, JSON.stringify(user))
      .map((response: Response) => response.json()._embedded.userResources)
      .catch((err: any) => Observable.throw(err.json().error || 'Server error'));
  }

  deleteUser(user: User): Observable<User> {
    //TODO rukl@all implement
    return null;
  }

  updateUser(user: User): Observable<User> {
    //TODO rukl@all implement
    return null;
  }
}
