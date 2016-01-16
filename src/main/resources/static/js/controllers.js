springSecurityAngular.controller('authController', ['$scope' ,'$location','$log' , 'authService', function($scope ,$location ,$log ,authService) {

    $scope.login = function (){
        authService.login($scope.username, $scope.password)
            .then(
                function(){
                    $location.path("/");
                },
                function(data){
                     $log.error("Error While Loging "+ data);
                }
            );
    }

    $scope.logout = function(){
        authService.logout()
            .then(
                function(){
                    $location.path("/login");
                },
                function(data){
                    $log.error("Error While Loging "+ data);
                }
            );;
    }

}]);

springSecurityAngular.controller('homeController', ['$scope' ,'$rootScope' , function($scope ,$rootScope) {

}]);