import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-budgeting-banner',
  templateUrl: './budgeting-banner.component.html'
})
export class BudgetingBannerComponent implements OnInit {

  @Input() carryover: number;
  @Input() income: number;
  @Input() plannedBudget: number;
  budgetToAllocate: number;

  constructor() {
  }

  ngOnInit() {
  }

}
