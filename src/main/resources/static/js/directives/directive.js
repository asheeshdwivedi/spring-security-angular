springSecurityAngular.directive("emailForm" ,function(){
    return {
        restrict :'E',
        templateUrl : 'partials/directives/emailForm.html',
        replace : true,
    }

});

springSecurityAngular.directive("confirmPassword" ,function(){
    return {
        restrict :'E',
        templateUrl : 'partials/directives/confirmPassword.html',
        replace : true,
    }

});

springSecurityAngular.directive("confirmPasswordCheck" , function(){

    return {
        require: 'ngModel',
        link: function (scope, elem, attrs, ctrl) {
            var firstPassword = '#' + attrs.confirmPasswordCheck;
            elem.add(firstPassword).on('keyup', function () {
                scope.$apply(function () {
                    ctrl.$setValidity('confirmPassMatch', elem.val() === $(firstPassword).val());
                });
            });
         }
        }
});