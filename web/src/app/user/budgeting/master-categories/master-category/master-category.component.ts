import {Component, Input, OnInit} from '@angular/core';
import {MasterCategory} from "../../../../model/budgeting";

@Component({
  selector: 'app-master-category',
  templateUrl: './master-category.component.html',
  styles: []
})
export class MasterCategoryComponent implements OnInit {

  @Input() masterCategory: MasterCategory;

  planned: number;
  spending: number;
  difference: number;
  previousPlanned: number;
  previousSpending: number;
  average: number;


  constructor() {
  }

  ngOnInit() {
    this.planned = this.getPlannedSum();
    this.spending = this.getSpendingsSum();
    this.difference = this.planned - this.spending;

    this.previousPlanned = this.getPreviousPlannedSum();
    this.previousSpending = this.getPreviousSpendingSum();
    this.average = this.getAverageSum();
  }

  getPlannedSum(): number {
    let sum = 0;
    for (let entry of this.masterCategory._embedded) {
      sum += entry.budget;
    }

    return sum;
  }

  getSpendingsSum(): number {
    let sum = 0;
    for (let entry of this.masterCategory._embedded) {
      sum += entry.expenses;
    }

    return sum;
  }

  getPreviousSpendingSum(): number {
    let sum = 0;
    for (let entry of this.masterCategory._embedded) {
      sum += entry.previousExpenses;
    }

    return sum;
  }

  getPreviousPlannedSum(): number {
    let sum = 0;
    for (let entry of this.masterCategory._embedded) {
      sum += entry.previousBudget;
    }

    return sum;
  }

  getAverageSum(): number {
    let sum = 0;
    for (let entry of this.masterCategory._embedded) {
      sum += entry.averageExpenses;
    }

    return sum;
  }




}
