import {Injectable} from '@angular/core';
import {Http} from "@angular/http";
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

}
