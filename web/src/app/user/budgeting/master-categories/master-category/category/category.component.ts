import {Component, Input, OnInit} from '@angular/core';
import {Category} from "../../../../../model/budgeting";

@Component({
  selector: 'app-category-entry',
  templateUrl: './category.component.html',
  styles: []
})
export class CategoryComponent implements OnInit {

  @Input() category: Category;

  constructor() {
  }

  ngOnInit() {
  }

  getDifference(): number {
    return this.category.budget - this.category.expenses;
  }

}
