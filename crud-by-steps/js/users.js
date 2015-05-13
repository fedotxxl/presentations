angular.module("app").
    factory("_usersApi", function ($http, _router) {
        return {
            list: function () {
                return $http.get(_router.users());
            },
            add: function (user) {
                return $http.post(_router.users(user.mail), user);
            }
        }
    }).
    factory("_users", function (_usersApi) {
        return {
            list: function () {
                return _usersApi.list().then(function (response) {
                    return response.data;
                })
            },
            add: function (user) {
                return _usersApi.add(user).then(function (response) {
                    return response.data;
                })
            }
        }
    }).
    controller("UsersController", function ($scope, _users) {
        _users.list().then(function (users) {
            $scope.users = users;
        });
    }).
    controller("UserAddController", function ($scope, _users) {
        $scope.user = {};

        $scope.save = function (user) {
                _users.add($scope.user)
                    .then(function (user) {
                        $scope.users.push(user);
                        $scope.user = {};
                    });
        };
    });