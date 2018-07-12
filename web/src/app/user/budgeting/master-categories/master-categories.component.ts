import {Component, Input, OnInit} from '@angular/core';
import {MasterCategory} from "../../../model/budgeting";

@Component({
  selector: 'app-master-categories',
  templateUrl: './master-categories.component.html',
  styles: []
})
export class MasterCategoriesComponent implements OnInit {

  @Input() masterCategories: MasterCategory[];

  constructor() {
  }

  ngOnInit() {
  }

}