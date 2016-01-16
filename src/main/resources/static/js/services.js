springSecurityAngular.factory('authService', ['$http', "$q" ,"$rootScope", function ($http, $q , $rootScope) {
    return {
        login: function (username, password) {
            return $http.post("/login", 'username=' + username + '&password=' + password, {
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                }).then(
                    function (response) {

                    },
                    function (errResponse) {
                        return $q.reject(errResponse);
                    }
                );

        },

        logout : function(){
            return $http.post("/auth/logout")
                .then(
                    function (response) {
                        delete $rootScope.user ;
                    },
                    function (errResponse) {
                        return $q.reject(errResponse);
                    }
                );
        },
        getLoggedInUserDetails : function(){
            return $http.post('/getLoggedInUserDetails')
                .then(
                    function (response) {
                        $rootScope.user = response.data;
                        return response.data;
                    },
                    function (errResponse) {
                        return $q.reject(errResponse);
                    }
                );
        }
    }
}]);

