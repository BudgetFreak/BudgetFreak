import {Component, OnInit} from '@angular/core';
import {BudgetingService} from "./budgeting.service";
import {Budgeting} from "../../model/budgeting";

@Component({
  selector: 'app-budgeting',
  templateUrl: './budgeting.component.html'
})
export class BudgetingComponent implements OnInit {

  budgeting: Budgeting;

  constructor(private budgetingService: BudgetingService) {
  }

  ngOnInit() {
    this.budgeting = this.budgetingService.getBudgeting(2018, 7);
  }

}
