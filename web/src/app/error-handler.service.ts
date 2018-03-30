import {User} from "./model/model-interfaces";
import {Response} from "@angular/http";
import {Observable} from "rxjs/Observable";

/**
 * Service for central error handling
 * ToDo: Discuss a method in team to pass on errors to the surface and apply code here
 */
export class ErrorHandlerService {

  constructor() { }

  static handleError(error: any) {
    const errMsg = ((error.body || {}).message) ? error.message :
      error.status ? `${error.status} - ${error.statusText}` : 'Server error';
    console.error(errMsg);
    console.error(error);
    return Observable.throw(errMsg);
  }

}
