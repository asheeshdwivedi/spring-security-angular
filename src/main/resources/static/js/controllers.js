springSecurityAngular.controller('authController', ['$scope' ,'$state','messageService' , 'authService', function($scope ,$state ,messageService ,authService) {

    $scope.login = function (){
        authService.login($scope.username, $scope.password)
            .then(function(){
                   $state.go("home");
                })
            .catch(function(data){
                    messageService.error("LOGIN_FAILURE", "The email and password you entered don't match");
                });

    }
}]);

springSecurityAngular.controller('homeController', ['$scope' , function($scope) {

}]);

springSecurityAngular.controller( 'AppCtrl',['$scope' , function AppCtrl ($scope) {
    $scope.$on('$stateChangeSuccess', function(event, toState, toParams, fromState, fromParams){
        if ( angular.isDefined( toState.data.pageTitle ) ) {
            $scope.pageTitle = toState.data.pageTitle + ' | Spring Security Angular' ;
        }
    });
}]);

springSecurityAngular.controller('manageUser', ['$scope' ,'userService' , function($scope , userService) {
       $scope.gridOptions = {
           columnDefs: [
               { field: 'id' },
               { field: 'email' },
               { field: 'password'},
               { field: 'firstName'},
               { field: 'lastName'},
               { field: 'createdBy'},
               { field: 'createdAt'},
               { field: 'updatedBy'},
               { field: 'updatedAt'},
           ],
           enableGridMenu: true,

           enableSelectAll: true,

           exporterCsvFilename: 'user.csv',

           exporterPdfDefaultStyle: {fontSize: 9},

           exporterPdfTableStyle: {margin: [0, 0, 30, 30]},

           exporterPdfTableHeaderStyle: {fontSize: 10, bold: true, italics: true, color: 'red'},

           exporterPdfHeader: { text: "Users", style: 'headerStyle' },

           exporterPdfFooter: function ( currentPage, pageCount ) {
               return { text: currentPage.toString() + ' of ' + pageCount.toString(), style: 'footerStyle' };
           },
           exporterPdfCustomFormatter: function ( docDefinition ) {
               docDefinition.styles.headerStyle = { fontSize: 22, bold: true };
               docDefinition.styles.footerStyle = { fontSize: 10, bold: true };
               return docDefinition;
           },
           exporterPdfOrientation: 'portrait',
           exporterPdfPageSize: 'LETTER',
           exporterPdfMaxGridWidth: 500,
           exporterCsvLinkElement: angular.element(document.querySelectorAll(".custom-csv-link-location")),

           onRegisterApi: function(gridApi){
               $scope.gridApi = gridApi;
           }

       }
        userService.findAllUser()
          .then(function(data){
                    $scope.gridOptions.data = data;
            }
          ).catch(
            function(data){
                console.error("Error While Loging "+ data);
            }
        );
}]);

springSecurityAngular.controller('createUser', ['$scope' , function($scope) {
 console.log("............");
}]);

springSecurityAngular.controller('forgotPassword', ['$scope','$stateParams' ,'messageService' , 'emailService' ,'userService' , function($scope,$stateParams,messageService, emailService ,userService) {
    $scope.email = $stateParams.email;

    $scope.sendResetPasswordLink = function (){
        emailService.send(true ,$scope.email)
            .then(function(){
                messageService.info("MAIL_SEND_FAIL" , "Check your email for a link to reset your password. If it doesn't appear within a few minutes, check your spam folder.");
        }).catch(function(data){
            messageService.error("MAIL_SEND_FAIL" , "Could able to send message, sorry.");
        });
    }
}]);

springSecurityAngular.controller('resetPassword', ['$scope','$state' ,'$stateParams','messageService' , 'userService', function($scope, $state, $stateParams , messageService, userService) {

    $scope.resetPassword= function () {
        userService.resetPassword($stateParams.email, $scope.password)
                    .then(function(){
                           $state.go("login");
                        })
                    .catch(function(data){
                            messageService.error("Reset_Password_failed" ,"Could not able to reset password, Sorry");
                        });
        }

}]);
