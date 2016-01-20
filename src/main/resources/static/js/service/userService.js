'use strict';

springSecurityAngular.service('userService', ['$http', "$q",'propertiesConstant' , function ($http, $q ,propertiesConstant) {

    this.findAllUser = function  () {
        var d = $q.defer();

        $http.get(propertiesConstant.URL_PRFIX + "/findAllUser")
            .success(function (data, status, headers, config) {
                d.resolve(data);
            })
            .error(function () {
                d.reject();
            });

        return d.promise;
    };

    this.findByEail = function getLoggedInUserDetails(email) {
        var d = $q.defer();
        $http.get(propertiesConstant.URL_PRFIX + "/users/"+email)
            .success(function (data, status, headers, config) {
                d.resolve(data);
            })
            .error(function () {
                d.reject();
            });
        return d.promise;
    };

    this.getLoggedInUserDetails = function getLoggedInUserDetails() {
        var d = $q.defer();

        $http.get(propertiesConstant.URL_PRFIX + "/getLoggedInUserDetails")
            .success(function (data, status, headers, config) {
                d.resolve(data);
            })
            .error(function () {
                d.reject();
            });
        return d.promise;
    };


    this.resetPassword = function (email, password) {
        var d = $q.defer();
        $http.post(propertiesConstant.URL_PRFIX +"/resetPassword/"+email +"/", "password="+password, {
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


}]);