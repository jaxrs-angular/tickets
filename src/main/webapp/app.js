define(['angular','bootstrapUi', 'bootstrap', 'angularMocks', 'angularCookies'], function (angular,bootstrapUi) {
    var app = angular.module('app', ['ngCookies', 'inform', 'ui.bootstrap']);
    return app;
});
