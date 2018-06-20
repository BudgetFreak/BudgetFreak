/**
 * Represents a user account that manages all the budgets of a person or a household.
 */
export interface User {
  name?: string;
  currency?: string;
  _links?: UserLinks
}

/**
 * Contains links to that user itself and his accounts.
 */
export interface UserLinks {
  self?: string;
  accounts?: string;
}

/**
 * Represents an account.
 */
export interface Account {
  name?: string;
}

export interface Budget {
  carryover?: number;
  income?: number;
  masterCategories?: MasterCategory[];
  _links?: BudgetLinks
}

export interface BudgetLinks {
  self?: string;
}

export interface MasterCategory {
  name?: string,
  categories?: Category[]
}

export interface Category {
  name?: string,
  planned?: number,
  spending?: number,
  previousPlanned?: number,
  previousSpending?: number,
  averageSpending?: number,
  _links: {
    self?: string 
  }
}
