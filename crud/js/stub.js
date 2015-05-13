angular.module('app')
    .config(function ($provide) {
        $provide.decorator('$httpBackend', angular.mock.e2e.$httpBackendDecorator);
    })
    .factory("_stub", function () {
        var users = [
            {
                mail: "a@a.ru"
            },
            {
                mail: "a@b.ru"
            },
            {
                mail: "a@c.ru"
            }
        ];

        return {
            users: users
        }
    })
    .config(function($provide) {
        //https://endlessindirection.wordpress.com/2013/05/18/angularjs-delay-response-from-httpbackend/
        $provide.decorator('$httpBackend', function($delegate) {
            var proxy = function(method, url, data, callback, headers) {
                var interceptor = function() {
                    var _this = this,
                        _arguments = arguments;
                    setTimeout(function() {
                        callback.apply(_this, _arguments);
                    }, 700);
                };
                return $delegate.call(this, method, url, data, interceptor, headers);
            };
            for(var key in $delegate) {
                proxy[key] = $delegate[key];
            }
            return proxy;
        });
    })
    .run(function ($httpBackend, _router, _stub) {
        $httpBackend.whenGET(_router.users()).respond(_stub.users);
        $httpBackend.whenPOST(_router.users()).respond(function (method, url, json, headers) {
            var data = JSON.parse(json);
            var user = _.find(_stub.users, function(user) {
                return user.mail == data.mail;
            });

            if (user) {
                return [409, {error: "Already have this user"}, {}, 'TestPhrase'];
            } else {
                return [200, data];
            }
        });
    });