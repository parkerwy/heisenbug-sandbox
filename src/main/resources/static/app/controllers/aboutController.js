var app = angular.module('App');

app.controller('aboutController', function ($scope, $log, $http) {

    $scope.info = {};
    $scope.loadInfo = function () {
        $http.get('info.json')
            .then(function successCallback(response) {
                $scope.info = response.data;
                $log.info('actuator info loaded.');
            }, function errorCallback(response) {
                $log.error(response);
            })
    };

    $scope.health = {};
    $scope.loadHealth = function () {
        $http.get('health.json')
            .then(function successCallback(response) {
                $scope.health = response.data;
                $log.info('actuator health loaded.');
            }, function errorCallback(response) {
                $log.error(response);
            })
    };

    $scope.mappings = {};
    $scope.loadMappings = function () {
        $http.get('mappings.json')
            .then(function successCallback(response) {
                $scope.mappings = response.data;
                delete $scope.mappings["_links"];
                $log.info('actuator mappings loaded.');
            }, function errorCallback(response) {
                $log.error(response);
            })
    };

    $scope.metrics = {};
    $scope.loadMetrics = function () {
        $http.get('metrics.json')
            .then(function successCallback(response) {
                $scope.metrics = response.data;
                $log.info('actuator metrics loaded.');
            }, function errorCallback(response) {
                $log.error(response);
            })
    };

    $scope.env = {};
    $scope.loadEnv = function () {
        $http.get('env.json')
            .then(function successCallback(response) {
                $scope.env = response.data;
                $log.info('actuator env loaded.');
            }, function errorCallback(response) {
                $log.error(response);
            })
    };
});