# UI

## How we got here

### Before
Simple jquery frontend

#### pros:
* simple to implement

#### cons:
* hard to support (spagetti code)
* slow frontend → each action require server to rerender page
* build system depends on server implementation

### Long way to choose technologies
* wav.tv → jquery → marrionettejs → anglarjs + reactjs
* ero-video.net → jquery → marrionettejs → angularjs?
* ero-pic.net (started 2015) → angularjs
* punyu (admin) (started 2016) → angularjs

### Where are we?
Frontend became more complicated because it server more (features).
**We solve this complexity with appropriate technologies.**

### Our current technologies
* CSS → SCSS
* GULP as build system (compiles SCSS / joins / minified css / js)
* AngularJS as JS framework
* ng-admin as admin building framework

### Where are we (pros / cons)
#### pros:
* faster fronted → less data to transfer by network
* faster development process → we can implement / deploy frontend / backend independently
* Separate frontend / backend:
** different developers for frontend and backend
** easier to support

#### cons:
* search engines (right now google tries to solve this problem)
* Developer should remember about performance (most popular elements may be implemented with ReactJS)


### Why?
#### SCSS / GULP / AngularJS → leading technologies
in general it means that it is good
* less bugs
* more information / larger community
* faster development
* easy to find developers

#### ng-admin
* easy way to create common admin pages
* configurable
* less code to write → less bugs → faster development

### Where we want to go
Technologies evolves all the time to work faster and better...

#### This year:
* ES6 (next version of JavaScript)
* AngularJS2

#### Next year:
… will see