var app = angular.module('App');

app.controller('homeController', function ($scope, $log, $http, $cookies) {

    $scope.csrfToken = $cookies.get('XSRF-TOKEN');

    $scope.logout = function () {

        $http.post('logout')
            .then(function successCallback(response) {
                $log.info('logged out.');
                $log.info(response.headers());
            }, function errorCallback(response) {
                $log.error(response);
            })
    }
});