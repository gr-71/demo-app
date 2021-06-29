angular.module('app').controller('ordersController', function ($scope, $http, $location) {
    const contextPath = 'http://localhost:8189/app';

    fillTable = function () {
        $http.get(contextPath + '/cart')
            .then(function (response) {
                $scope.CartList = response.data;
            });
    };

    $scope.confirmOrder = function () {
        console.log($scope.client.address);
        $http({
            url: contextPath + '/orders/confirm',
            method: "POST",
            params: {address: $scope.client.address}
        }).then(function () {
            $location.path('/orders');
        });
    }

    fillTable();
});