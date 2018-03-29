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
 * Represent an account.
 */
export interface Account {
  name?: string;
}

