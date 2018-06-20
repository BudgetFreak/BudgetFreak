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
      },
      {
        name: 'Freizeit',
        entries: [
          {
            category: {name: 'Fotografie'},
            budget: 50,
            expenses: 35
          },
          {
            category: {name: 'Spiele'},
            budget: 350,
            expenses: 25
          },
          {
            category: {name: 'Bücher'},
            budget: 30,
            expenses: 9.99
          }

        ]
      },
      {
        name: 'Essen',
        entries: [
          {
            category: {name: 'Cafes & Restaurants'},
            budget: 200,
            expenses: 65
          },
          {
            category: {name: 'Mittagessen'},
            budget: 150,
            expenses: 45
          }
          

        ]
      },
      {
        name: 'Auto',
        entries: [
          {
            category: {name: 'Versicherung'},
            budget: 80,
            expenses: 0
          },
          {
            category: {name: 'Leasing'},
            budget: 350,
            expenses: 350
          },
          {
            category: {name: 'Wartung'},
            budget: 100,
            expenses: 0
          }        

        ]
      },
      {
        name: 'Abonements',
        entries: [
          {
            category: {name: 'Spotify'},
            budget: 9.99,
            expenses: 0
          },
          {
            category: {name: 'Amazon Prime'},
            budget: 7.99,
            expenses: 0
          },
          {
            category: {name: 'Audible'},
            budget: 9.99,
            expenses: 0
          }        

        ]
      }
    ];
  }

}
