# UI

## How we got here

### Before
Simple jquery frontend

#### pros:
* simple to implement

#### cons:
* hard to support (spaghetti code)
* slow frontend → a lot of traffic on each user action
* build system depends on server implementation

### Long way to choose technologies
* wav.tv → jquery → marrionettejs → anglarjs + reactjs
* ero-video.net → jquery → marrionettejs → angularjs?
* ero-pic.net (started 2015) → angularjs
* punyu (admin) (started 2016) → angularjs

### Where are we?
Frontend became more complicated because it serves more (features).
**We solve this complexity with appropriate technologies.**

### Our current technologies
We try to use same technologies on all new projects / move old projects to new technologies set (if it makes sense)

* CSS → SCSS
* GULP as build system (compiles SCSS / joins / minified css / js)
* AngularJS as JS framework
* ng-admin as admin building framework

### Why?
#### SCSS / GULP / AngularJS → leading technologies
* in general it means that it is good
* faster development
* larger community
** less bugs
** more information
** easy to find developers

#### ng-admin
* easy way to create common admin pages
* configurable
* less code to write → less bugs → faster development

### Where are we (pros / cons)
#### pros:
* faster fronted → less data to transfer by network
* faster development process → we can implement / deploy frontend / backend independently
* Separate frontend / backend:
** different developers for frontend and backend
** easier to support

#### cons:
* search engines (right now google tries to solve this problem)
* developer should remember about performance (most popular elements may be implemented with ReactJS)

### Where we want to go
Technologies evolves all the time... Our goal:

* Write maintainable code
* Fast dev / deploy cycle
* Use same techs on all projects
* (sometimes) write less code

=> be more productive and efficient

#### This year:
* ES6 (next version of JavaScript)
* AngularJS2

#### Next year:
… will see