
var springSecurityAngular = angular.module('springSecurityAngular', ['ngRoute', 'ngResource']);

// ROUTES
springSecurityAngular.config(function ($routeProvider , $httpProvider ,$provide ) {

    $routeProvider

        .when('/', {
            templateUrl: 'partials/home.html',
            controller: 'homeController',
            resolve : {
                load: function ($route, UserService , $rootScope) {
                    return UserService.getUser().then(function(user){
                        $rootScope.user = user;
                    });
                }
            }
        })
        .when('/login', {
            templateUrl: 'partials/login.html',
            controller: 'loginController'
        })

        .when('/logout',{
            controller: 'logoutController',
            templateUrl: 'partials/login.html'
        });


    $httpProvider.interceptors.push(function ($q, $rootScope, $location) {
            return {
                response: function(response){
                    if (response.status === 401) {
                        console.log("Response 401");
                    }
                    return response || $q.when(response);
                },
                'responseError': function(rejection) {
                    var status = rejection.status;
                    var config = rejection.config;
                    var method = config.method;
                    var url = config.url;

                    if (status == 401) {
                        $location.path( "/login" );
                    } else {
                        $rootScope.error = method + " on " + url + " failed with status " + status;
                    }

                    return $q.reject(rejection);
                }
            };
        }
    );

    $provide.decorator('$templateRequest', ['$delegate', function($delegate) {
        var fn = $delegate;
        $delegate = function(tpl) {

            for (var key in fn) {
                $delegate[key] = fn[key];
            }
            return fn.apply(this, [tpl, true]);
        };
        return $delegate;
    }]);

}).run(function($rootScope , $location ,UserService){

    $rootScope.hasRole = function(role) {
        console.log("calling has Role" );
        if ($rootScope.user === undefined) {
            return false;
        }

        if ($rootScope.user.roles[role] === undefined) {
            return false;
        }

        return $rootScope.user.roles[role];
    };


    $rootScope.isLoggedIn = function(){
        return $rootScope.user !== undefined ;
    };

    if($rootScope.user === undefined){
        UserService.getUser().then(function(user){
            $rootScope.user = user;
        });
    }

});


springSecurityAngular.service('sessionService', ['$http' ,'$location' ,'$resource' ,"$rootScope",function($http ,$location,$resource,$rootScope){
    var session = {};
    session.login = function(data) {
        var self = this;
        return $http.post("/login" ,'username='+data.userName+'&password='+data.password ,
             {
            headers: {'Content-Type': 'application/x-www-form-urlencoded'}
        } ).then(function(data) {
            $location.path( "/" );
        }, function(data) {
            alert("error logging in");
        });
    };
    session.logout = function() {
        delete $rootScope.user;
        return $http.post("/auth/logout")
            .then(function(data) {
                $location.path("/");
            }, function(data) {
                alert("error logout in");
        });
    };
    return session;

}]);




springSecurityAngular.controller('logoutController', ['$scope' ,'sessionService', function($scope , sessionService) {
    sessionService.logout();
}]);

springSecurityAngular.controller('loginController', ['$scope' ,'sessionService', '$rootScope','UserService', function($scope , sessionService ,$rootScope , UserService) {

    $scope.login = function (){
        sessionService.login({
            userName: $scope.username,
            password:$scope.password
        });
    }
}]);

springSecurityAngular.factory('UserService', function($http , $rootScope) {

    return{
        getUser : function(){
           return $http.post('/user', {},
                {
                    headers : {'Content-Type': 'application/x-www-form-urlencoded'}
                }
            ).then(function(response){
               return response.data;
            },function(data){
                console.log("Error !!!!")
            });
        }
    }


});

springSecurityAngular.controller('homeController', ['$scope' ,'$rootScope' ,'UserService', function($scope ,$rootScope ,UserService) {

}]);








