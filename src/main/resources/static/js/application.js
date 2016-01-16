var springSecurityAngular = angular.module('springSecurityAngular', ['ngRoute', 'ngResource','ui.grid' ,'ui.grid.selection', 'ui.grid.exporter']);

// ROUTES
springSecurityAngular.config(function ($routeProvider, $httpProvider, $provide) {

    $routeProvider
        .when('/', {
            templateUrl: 'partials/home.html',
            controller: 'homeController',
            resolve: {
                load: function (authService, $rootScope) {
                    return authService.getLoggedInUserDetails();
                }
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
                load: function (authService, $rootScope) {
                    return authService.logout();
                }
            }
        })
        .when('/manageUser' ,{
            controller: 'manageUser',
            templateUrl: 'partials/manageUser.html',
        });

    $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
            return {
                response: function (response) {
                    if (response.status === 401) {
                        console.log("Response 401");
                    }
                    return response || $q.when(response);
                },
                'responseError': function (rejection) {
                    var status = rejection.status;
                    var config = rejection.config;
                    var method = config.method;
                    var url = config.url;
                    if (status == 401) {
                        $location.path("/login");
                    } else {
                        $rootScope.error = method + " on " + url + " failed with status " + status;
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

}).run(function ($rootScope, $location, authService) {

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

    if(!$rootScope.user){
        authService.getLoggedInUserDetails();
    }


});













