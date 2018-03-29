import {Observable} from "rxjs/Observable";
import {Injectable} from "@angular/core";
import {Http, Response} from "@angular/http";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import {User} from "../model/model-interfaces";

@Injectable()
export class UserService {

  private apiUrl = 'http://localhost:8080/users';

  constructor(private http: Http) {

  }

  findAll(): Observable<User[]> {
    return this.http.get(this.apiUrl)
      .map((response: Response) => response.json()._embedded.userResources)
      .catch((err: any) => Observable.throw(err.json().error || 'Server error'));
  }

  findById(id: Number): Observable<User> {
    //TODO rukl@all implement
    return null;
  }

  createUser(user: User): Observable<User> {
    //TODO rukl@all implement
    return null;
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
