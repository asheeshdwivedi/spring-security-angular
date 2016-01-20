/**
 * Created by asheeshdwivedi on 1/20/16.
 */

springSecurityAngular.service('emailService', ['$http', "$q" ,'propertiesConstant',function ($http , $q, propertiesConstant) {


    this.send = function(wait , email){
        var d = $q.defer();
        $http.post(propertiesConstant.URL_PRFIX +"/send-mail/"+email+"/", 'wait='+wait, {})
            .success(function (data, status, headers, config) {
                d.resolve(data);
            })
            .error(function () {
                d.reject();
            })
        return d.promise;
    }


}]);