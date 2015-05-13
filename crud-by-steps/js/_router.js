angular.module("app").
    factory("_router", function() {
        return {
            users: function() {
                return "/users/"
            }
        }
    });