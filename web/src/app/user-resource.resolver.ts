import {User} from "./model/model-interfaces";
import {Response} from "@angular/http";

export class UserResourceResolver {

  constructor() { }

  static resolveCollection(response: Response) : User[] {
    return response.json()._embedded.userResources;
  }

}
