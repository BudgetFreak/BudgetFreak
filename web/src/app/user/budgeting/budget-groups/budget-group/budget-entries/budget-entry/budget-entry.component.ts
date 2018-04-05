import {Component, Input, OnInit} from '@angular/core';
import {BudgetEntry} from "../../../../../../model/model-interfaces";

@Component({
  selector: 'app-budget-entry',
  templateUrl: './budget-entry.component.html',
  styles: []
})
export class BudgetEntryComponent implements OnInit {

  @Input() budgetEntry: BudgetEntry;

  constructor() {
  }

  ngOnInit() {
  }

  getDifference(): number {
    return this.budgetEntry.budget - this.budgetEntry.expenses;
  }

}
