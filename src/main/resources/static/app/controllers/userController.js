var app = angular.module('App');

app.controller('userController', function ($scope, $log, $http, $cookies) {

    $scope.user = {};

    $scope.csrfToken = $cookies.get('XSRF-TOKEN');

    $scope.logout = function () {

        $http.post('logout')
            .then(function successCallback(response) {
                $log.info('logged out.');
                $log.info(response.headers());
            }, function errorCallback(response) {
                $log.error(response);
            });
    }

    $http.get('user.json')
        .then(function successCallback(response) {
            $scope.user = response.data;
        }, function errorCallback(response) {
            $log.error(response);
        });
});