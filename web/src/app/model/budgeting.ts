export interface BudgetingLinks {
  self: string;
  previous?: string;
  next?: string;
}

interface CategoryLinks {
  self: string;
}

export interface Category {
  name: string,
  budget?: number,
  expenses?: number,
  previousBudget?: number,
  previousExpenses?: number,
  averageExpenses?: number,
  _links: CategoryLinks;
}

interface MasterCategoryLinks {
  self: string;
}

export interface MasterCategory {
  name: string;
  _links: MasterCategoryLinks;
  _embedded: Category[];
}

export interface Budgeting {
  carryover?: number;
  income?: number;
  _links?: BudgetingLinks;
  _embedded: MasterCategory[]

}

