'use strict';

springSecurityAngular.service('userService', ['$http', "$q", function ($http, $q) {

    this.findAllUser = function  () {
        var d = $q.defer();

        $http.get("/findAllUser")
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

        $http.get("/getLoggedInUserDetails")
            .success(function (data, status, headers, config) {
                d.resolve(data);
            })
            .error(function () {
                d.reject();
            });
        return d.promise;
    };


}]);