import {BUDGETING} from "../../mock-data/mock-budgeting";
import {Budgeting} from "../../model/budgeting";
import {Injectable} from "@angular/core";


@Injectable({
  providedIn: 'root'
})
export class BudgetingService {

  getBudgeting(year: number, month: number): Budgeting {
    return BUDGETING;
  }

}
