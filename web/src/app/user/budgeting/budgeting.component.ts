import { Component, OnInit } from '@angular/core';
import { MasterCategory } from "../../model/model-interfaces";

@Component({
  selector: 'app-budgeting',
  templateUrl: './budgeting.component.html'
})
export class BudgetingComponent implements OnInit {

  masterCategories: MasterCategory[];

  constructor() {

  }

  ngOnInit() {
    this.masterCategories = [
      {
        name: 'Wohnen',
        categories: [
          {
            name: 'Miete',
            planned: 600,
            spending: 560,
            previousPlanned: 300,
            previousSpending: 240,
            averageSpending: 50,
            _links: {}
          },
          {
            name: 'Strom',
            planned: 50,
            spending: 45,
            previousPlanned: 49,
            previousSpending: 45,
            averageSpending: 42,
            _links: {}
          },
          {
            name: 'Internet',
            planned: 40,
            spending: 39.9,
            previousPlanned: 42,
            previousSpending: 40,
            averageSpending: 35,
            _links: {}
          }

        ]
      }
    ]
    
  }

}
