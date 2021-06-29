angular.module('app').controller('goodsController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/app';

    fillTable = function () {
        $http.get(contextPath + '/goods')
            .then(function (response) {
                $scope.ProductsList = response.data;
            });
    };

    $scope.addToCartFunction = function(goods) {
        $http({
            url: contextPath + '/cart/add/' + goods.id,
            method: "GET"
        }).then(function (response) {
            console.log('added');
        });
    }

    fillTable();
});