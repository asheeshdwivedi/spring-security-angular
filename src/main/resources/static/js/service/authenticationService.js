'use strict';

springSecurityAngular.service('authService', ['$http', "$q", "$rootScope" ,'propertiesConstant', function ($http, $q, $rootScope ,propertiesConstant) {

    this.login = function (username, password) {
        var d = $q.defer();

        $http.post(propertiesConstant.URL_PRFIX +"/login", 'username=' + username + '&password=' + password, {
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
    this.forgotPassword = function (username) {
            var d = $q.defer();

            $http.post(propertiesConstant.URL_PRFIX +"/send-mail/"+username+"/", 'wait=true', {
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
    this.resetPassword = function (username, password) {
             var d = $q.defer();
               console.log("username--- ",username, password)
             $http.post(propertiesConstant.URL_PRFIX +"/resetPassword", "username="+username+"&password="+password, {
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
        $http.post(propertiesConstant.URL_PRFIX + "/auth/logout")
            .success(function () {
                d.resolve();
            })
            .error(function () {
                d.reject();
            });
        return d.promise;
    };
}]);

