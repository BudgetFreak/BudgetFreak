/**
 * Represents a user account that manages all the budgets of a person or a household.
 */
export interface User {
  id?: number;
  name?: string;
  currency?: string;
  _links?: UserLinks
}

export interface UserLinks {
  self: string;
  accounts: string;
}

export interface Account {
  name?: string;
}
