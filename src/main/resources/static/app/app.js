var app = angular.module('App', ['ng', 'ngMaterial', 'ngRoute', 'ngCookies', 'md.data.table']);

app.config(function ($routeProvider) {

    $routeProvider
        .when('/', {
            templateUrl: 'views/homeView.html',
            controller: 'homeController'
        })

        .when('/about', {
            templateUrl: 'views/aboutView.html',
            controller: 'aboutController'
        })

        .otherwise({
            redirectTo: '/'
        });
});