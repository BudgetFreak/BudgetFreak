import {Component, Input, OnInit} from '@angular/core';
import {BudgetGroup} from "../../../model/model-interfaces";

@Component({
  selector: 'app-budget-groups',
  templateUrl: './budget-groups.component.html',
  styles: []
})
export class BudgetGroupsComponent implements OnInit {

  @Input() budgetGroups: BudgetGroup[];

  constructor() {
  }

  ngOnInit() {
  }

}
