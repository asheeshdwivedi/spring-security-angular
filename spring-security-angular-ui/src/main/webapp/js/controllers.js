springSecurityAngular.controller('authController', ['$scope' ,'$state','messageService' ,'propertiesConstant', 'authService', function($scope ,$state ,messageService ,propertiesConstant,authService) {

    $scope.login = function (){
        authService.login($scope.email, $scope.password)
            .then(function(){
                   $state.go("home");
                })
            .catch(function(data){
                    messageService.error(propertiesConstant.LOGIN_FAILURE_CODE, propertiesConstant.LOGIN_FAILURE_MESSAGE);
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
               { field: 'createdAt',cellFilter: 'date:\'MM/dd/yyyy\''},
               { field: 'updatedBy'},
               { field: 'updatedAt',cellFilter: 'date:\'MM/dd/yyyy\''},
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

springSecurityAngular.controller('forgotPassword', ['$scope','$stateParams' ,'propertiesConstant','messageService' , 'emailService' ,'userService' , function($scope,$stateParams,propertiesConstant,messageService, emailService ,userService) {
    $scope.email = $stateParams.email;

    $scope.sendResetPasswordLink = function (){
        emailService.send(true ,$scope.email)
            .then(function(){
                messageService.info(propertiesConstant.MAIL_SEND_FAIL_CODE , propertiesConstant.EMAIL_LINK_MESSAGE);
        }).catch(function(data){
             messageService.error(propertiesConstant.MAIL_SEND_FAIL_CODE, data.message);
        });
    }
}]);

springSecurityAngular.controller('resetPassword', ['$scope','$state' ,'$stateParams','propertiesConstant','messageService' , 'userService', function($scope, $state, $stateParams ,propertiesConstant, messageService, userService) {

    $scope.resetPassword= function () {
        userService.resetPassword($stateParams.email, $scope.password)
                    .then(function(){
                           $state.go("login");
                        })
                    .catch(function(data){
                             messageService.error(propertiesConstant.RESET_PASSWORD_FAILED_CODE, data.message);
                        });
        }

}]);
