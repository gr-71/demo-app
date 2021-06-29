angular.module('app').controller('cartController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/app';

    fillTable = function () {
        $http.get(contextPath + '/cart')
            .then(function (response) {
                $scope.CartList = response.data;
            });
    };

    $scope.createOrder = function () {
        $location.path('/create_order');
    }

    fillTable();
});