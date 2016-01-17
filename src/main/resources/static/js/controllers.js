springSecurityAngular.controller('authController', ['$scope' ,'$location','messageService' , 'authService', function($scope ,$location ,messageService ,authService) {

    $scope.login = function (){
        authService.login($scope.username, $scope.password)
            .then(function(){
                    $location.path("/");
                })
            .catch(function(data){
                    messageService.error("LOGIN_FAILURE", "The email and password you entered don't match");
                });

    }

}]);

springSecurityAngular.controller('homeController', ['$scope' , function($scope) {

}]);

springSecurityAngular.controller('manageUser', ['$scope' ,'userService' , function($scope , userService) {
       $scope.gridOptions = {
           columnDefs: [
               { field: 'id' },
               { field: 'email' },
               { field: 'password'}
           ],
           enableGridMenu: true,

           enableSelectAll: true,

           exporterCsvFilename: 'user.csv',

           exporterPdfDefaultStyle: {fontSize: 9},

           exporterPdfTableStyle: {margin: [30, 30, 30, 30]},

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
