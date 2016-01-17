var springSecurityAngular = angular.module('springSecurityAngular', ['ngRoute', 'ngResource', 'ui.grid', 'ui.grid.selection', 'ui.grid.exporter']);

// ROUTES
springSecurityAngular.config(function ($routeProvider, $httpProvider, $provide) {

    $routeProvider
        .when('/', {
            templateUrl: 'partials/home.html',
            controller: 'homeController',
            resolve: {
                user: getLoggedInUserDetails
            }
        })
        .when('/login', {
            templateUrl: 'partials/login.html',
            controller: 'authController'
        })

        .when('/logout', {
            controller: 'authController',
            templateUrl: 'partials/login.html',
            resolve: {
                clear: clear
            }
        })
        .when('/manageUser', {
            controller: 'manageUser',
            templateUrl: 'partials/manageUser.html',
        })
        .when('/createUser', {
            controller: 'createUser',
            templateUrl: 'partials/createUser.html',
        });

       function getLoggedInUserDetails($rootScope, userService) {
                userService.getLoggedInUserDetails()
                    .then(function (user) {
                        $rootScope.user = user;
                    });
        };

        function clear($rootScope , authService) {
            authService.logout()
                .then(function(){
                    delete $rootScope.user;
                }).catch(function(data){
                messageService.error("LOGOUT_FAILURE", "We were unable to log you out, please try again.");
            });

        };


    $httpProvider.interceptors.push(function ($q, $rootScope, $location, messageService) {
            return {
                'request': function (request) {
                    messageService.clearError();
                    return request;
                },
                'responseError': function (rejection) {
                    switch (rejection.status) {
                        case 400:
                        {
                            break;
                        }
                        case 401:
                        {
                            $location.path("/login");
                        }
                        case 403:
                        {
                            break;
                        }
                        case 500:
                        {
                            break;
                        }
                        default :
                        {
                            messageService.error("UNKNOWN_ERROR", "An error has occurred, please try again.");

                            break;
                        }
                    }
                    if (status == 401) {

                    }
                    return $q.reject(rejection);
                }
            };
        }
    );

    $provide.decorator('$templateRequest', ['$delegate', function ($delegate) {
        var fn = $delegate;
        $delegate = function (tpl) {

            for (var key in fn) {
                $delegate[key] = fn[key];
            }
            return fn.apply(this, [tpl, true]);
        };
        return $delegate;
    }]);

}).run(function ($rootScope, $location, userService) {

    $rootScope.hasRole = function (role) {
        if ($rootScope.user === undefined) {
            return false;
        }
        if ($rootScope.user.roles[role] === undefined) {
            return false;
        }
        return $rootScope.user.roles[role];
    };

    $rootScope.isLoggedIn = function () {
        return $rootScope.user !== undefined;
    };

    if (!$rootScope.user) {
        userService.getLoggedInUserDetails()
            .then(function (user) {
                $rootScope.user = user;
            });
    }


});













