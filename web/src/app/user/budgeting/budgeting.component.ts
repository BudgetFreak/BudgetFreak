import {Component, OnInit} from '@angular/core';
import {BudgetGroup} from "../../model/model-interfaces";

@Component({
  selector: 'app-budgeting',
  templateUrl: './budgeting.component.html'
})
export class BudgetingComponent implements OnInit {

  budgetGroups: BudgetGroup[];

  constructor() {

  }

  ngOnInit() {
    this.budgetGroups = [
      {
        name: 'Wohnen',
        entries: [
          {
            category: {name: 'Miete'},
            budget: 600,
            expenses: 560
          },
          {
            category: {name: 'Nebenekosten'},
            budget: 150,
            expenses: 125
          },
          {
            category: {name: 'Strom'},
            budget: 50,
            expenses: 42
          }

        ]
      }
    ];
  }

}
