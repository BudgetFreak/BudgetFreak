import { Component, Input, OnInit } from '@angular/core';
import {BudgetEntry} from "../../../../../model/model-interfaces";

@Component({
  selector: 'app-budget-entries',
  templateUrl: './budget-entries.component.html',
  styles: []
})
export class BudgetEntriesComponent implements OnInit {

  @Input() budgetEntries: BudgetEntry[];

  constructor() { }

  ngOnInit() {
  }

}
