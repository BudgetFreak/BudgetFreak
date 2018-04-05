import {Injectable} from '@angular/core';
import {Http, Response} from "@angular/http";
import {User} from "../model/model-interfaces";
import {UserResourceResolver} from "../user-resource.resolver";
import {Observable} from 'rxjs/Observable';
import {ErrorHandlerService} from "../error-handler.service";


const BASE_URL = 'http://localhost:8080/users/';

@Injectable()
export class UserService {

  constructor(private http: Http) {
  }

  findAll(): Observable<User[]> {
    return this.http.get(BASE_URL).map((response: Response) => UserResourceResolver.resolveCollection(response));
  }

  create(user: User): Observable<User> {
    return this.http
      .post(BASE_URL, JSON.stringify(user))
      .map((response: Response) => response.json())
      .catch(ErrorHandlerService.handleError);
  }

}
