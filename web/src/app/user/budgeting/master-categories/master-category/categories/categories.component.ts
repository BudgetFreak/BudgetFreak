import { Component, Input, OnInit } from '@angular/core';
import { Category } from "../../../../../model/model-interfaces";

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styles: []
})
export class CategoriesComponent implements OnInit {

  @Input() categories: Category[];

  constructor() { }

  ngOnInit() {
  }

}
