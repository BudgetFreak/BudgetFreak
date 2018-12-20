# Contributing

Since the project is in an early stage, the contributing guide will develop over time. For the time being you can use 
the following sections for some orientation.

## How to contribute?

It's too early to write this chapter beacuse there is a lot of things we just develop and discuss. If you want to 
help out building this app, just contact us. When you want to report a bugfix or propose a feature just open an issue.

We will review any pull request you propose. Before developing a new feature, please open an issue to discuss the new
feature beforehand.

## Tools

We're using IntelliJ (Java) and Visual Studio Code (Java / Typescript) to develop the project. You can import the project
as Gradle Project to get started. If you want to develop the web frontend just import the `web` folder 
in IntelliJ or VSC. 

## How to run things

TBA :smirk:

For the time being just run the server with the `bootrun` task which is placed under `BudgetFreak` > `Tasks` > 
`application` in the Gradle tool window in IntelliJ. If you want to start the web module use `ng serve` on your console.

## How to code

### Writing database migrations

We use Flyway to initialize and migrate our database.
For your migration scripts use the following form 
```
V<MAJOR_VERSION>_<MINOR_VERSION>_<PATCH_VERSION>_<INCREMENTED_INDEX>__<DESCRIPTION>.sql
```
and check the `flyway.locations` property in `application.properties` to find the correct path and `root build.gradle` 
for current versions.


## Styleguides

The code style will be checked via our CI build at some point. Until then please take account of the following 
guidelines.

### Git Commit Messages

* Use the present tense ("Add feature" not "Added feature")
* Use the imperative mood ("Move cursor to..." not "Moves cursor to...")
* Limit the first line to 72 characters or less
* Reference issues and pull requests liberally after the first line
* Consider starting the commit message with an applicable emoji:
    * :rocket:`:rocket:` when adding a feature
    * :art: `:art:` when improving the format/structure of the code
    * :racehorse: `:racehorse:` when improving performance
    * :memo: `:memo:` when writing docs
    * :bug: `:bug:` when fixing a bug
    * :fire: `:fire:` when removing code or files
    * :green_heart: `:green_heart:` when fixing the CI build
    * :white_check_mark: `:white_check_mark:` when adding tests
    * :lock: `:lock:` when dealing with security
    * :arrow_up: `:arrow_up:` when upgrading dependencies
    * :arrow_down: `:arrow_down:` when downgrading dependencies
    * :shirt: `:shirt:` when removing linter warnings
    
### Java Code

We're using IntelliJ to develop the backend. Just use the build-in formatter. Currenty we're evaluating other options
like using the Gradle Spotless plugin to check the Java code style.

### TypeScript

The Angular application was created via the Angular CLI. That's why there is EditConfig and TSLint present. Please
install the necessary plugins for respecting those rulsets.