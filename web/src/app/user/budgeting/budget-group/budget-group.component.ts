import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-budget-group',
  templateUrl: './budget-group.component.html',
  styles: []
})
export class BudgetGroupComponent implements OnInit {

  groupName: string;
  currentMonthBudgetSum: number;
  currentMonthTotalExpenses: number;
  currentMonthDifference: number;


  constructor() { }

  ngOnInit() {
  }

}
