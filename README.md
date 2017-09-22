# BudgetFreak

## Code Quality

* Builds on CircleCI
* Linting by sonarcloud: https://sonarcloud.io/organizations/budgetfreak-github/projects

## Flyway
We use Flyway to initialize and migrate our database.
For your migration scripts use the following form 
```
V<MAJOR_VERSION>_<MINOR_VERSION>_<PATCH_VERSION>_<INCREMENTED_INDEX>__<DESCRIPTION>.sql
```
and check the *flyway.locations* property in *application.properties* to find the correct path and *root build.gradle* for current versions