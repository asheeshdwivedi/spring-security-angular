'use strict';

springSecurityAngular.service('authService', ['$http', "$q", "$rootScope", function ($http, $q, $rootScope) {

    this.login = function (username, password) {
        var d = $q.defer();

        $http.post("/login", 'username=' + username + '&password=' + password, {
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
            })
            .success(function (data, status, headers, config) {
                d.resolve(data);
            })
            .error(function () {
                d.reject();
            })
        return d.promise;
    };

    this.logout = function () {
        var d = $q.defer();
        $http.post("/auth/logout")
            .success(function () {
                d.resolve();
            })
            .error(function () {
                d.reject();
            });
        return d.promise;
    };
}]);

