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

springSecurityAngular.controller('forgotPassword', ['$scope' ,'messageService' , 'authService', function($scope, messageService, authService) {
    $scope.forgotPassword = function (){
        authService.forgotPassword($scope.username)
            .then(function(){
                   messageService.error("Mail Sent");
                })
            .catch(function(data){
                    messageService.error("Not able to send mail");
                });

    }
}]);

springSecurityAngular.controller('resetPassword', ['$scope','$state' ,'messageService' , 'authService', function($scope, $state, messageService, authService) {
    $scope.resetPassword= function () {
        console.log("............",    getUrlVars()["email"], $scope.password);
        authService.resetPassword(getUrlVars()["email"], $scope.password)
                    .then(function(){
                           $state.go("login");
                        })
                    .catch(function(data){
                            messageService.error("Reset Password failed");
                        });
    }
   var getUrlVars = function() {
       var vars = {};
       var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi,
       function(m,key,value) {
         vars[key] = value;
       });
       return vars;
     }
}]);
