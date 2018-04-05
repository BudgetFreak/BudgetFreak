import {Component, Input, OnInit} from '@angular/core';
import {BudgetGroup} from '../../../../model/model-interfaces'

@Component({
  selector: 'app-budget-group',
  templateUrl: './budget-group.component.html',
  styles: []
})
export class BudgetGroupComponent implements OnInit {

  @Input() budgetGroup: BudgetGroup;

  budget: number;
  expenses: number;
  difference: number;


  constructor() {
  }

  ngOnInit() {
    this.budget = this.getBudgetSum();
    this.expenses = this.getExpensesSum();
    this.difference = this.budget - this.expenses;
  }

  getBudgetSum(): number {
    let sum = 0;
    for (let entry of this.budgetGroup.entries) {
      sum += entry.budget;
    }

    return sum;
  }

  getExpensesSum(): number {
    let sum = 0;
    for (let entry of this.budgetGroup.entries) {
      sum += entry.expenses;
    }

    return sum;
  }




}
