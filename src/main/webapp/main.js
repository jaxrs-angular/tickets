// configurando requirejs
require.config({
    paths: {
        jquery: '../vendor/jquery/dist/jquery',
        angular: '../vendor/angular/angular',
        angularMocks: '../vendor/angular-mocks/angular-mocks',
        angularCookies: '../vendor/angular-cookies/angular-cookies',
        bootstrap: '../vendor/bootstrap/dist/js/bootstrap',
        bootstrapUi: '../vendor/angular-bootstrap/ui-bootstrap-tpls',
        inform: '../vendor/angular-inform/dist/angular-inform',
        text: '../vendor/text/text',
        app: '../app'

    },
    shim: {
        spin: {
            deps: ['jquery', 'angular']
        },
        angular: {
            deps: ['jquery'],
            exports: 'angular'
        },
        angularMocks: {
            deps: ['angular'],
            exports: 'angular.mock'
        },
        angularCookies: {
            deps: ['angular']
        },
        bootstrap: {
            deps: ['jquery']
        },
        bootstrapUi: {
            deps: ["angular"],
            exports: "bootstrapUi"
        },
        inform: {
            deps: ['angular']
        }
    }
});
// inicializando controlador
require(['angular', 'bootstrap', 'bootstrapUi', 'app', 'angularMocks', 'angularCookies','inform'], function (angular, bootstrap, bootstrapUi, app) {
    angular.element().ready(function () {
        app.controller('mainCtrl', ['$scope', '$http', '$cookies', '$timeout','$uibModal','inform','$window', function ($scope, $http, $cookies, $timeout, $uibModal, inform, $window) {
            $scope.usuarioCorrente=undefined;
            $scope.text="OPS";
            $scope.lista = [];
            $scope.detalhe = undefined;


            $scope.isDetail = function(item){
                return angular.equals(item,$scope.detalhe);
            };

            $scope.select = function(item){
                if($scope.detalhe!=undefined && !angular.equals($scope.detalhe,$scope.original)){
                    $scope.openDialog(item);
                }else{
                    $scope.detalhe = item;
                    $scope.original = angular.copy(item);
                }
            };

            $scope.unassign = function(){
                $http.head("/task/unassign",{params:{id:$scope.detalhe.idTask}}).then(function(successResponse){
                    $scope.detalhe.owner=undefined;
                    $scope.original = angular.copy($scope.detalhe);
                    inform.add("Tarefa largada com sucesso!",{ttl:1000,type:"success"});
                },function(failResponse){
                    inform.add("Erro ao salvar a tarefa!",{ttl:2000,type:"error"});
                });

                $scope.detalhe.owner=undefined;
                $scope.original = angular.copy($scope.detalhe);
            };
            $scope.assign = function(){
                $http.head("/task/assign",{params:{id:$scope.detalhe.idTask}}).then(function(successResponse){
                    $scope.detalhe.owner=$scope.usuarioCorrente;
                    $scope.original = angular.copy($scope.detalhe);
                    inform.add("Tarefa Atribuída com sucesso!",{ttl:1000,type:"success"});
                },function(failResponse){
                    inform.add("Erro ao salvar a tarefa!",{ttl:2000,type:"error"});
                });


            };


            $scope.save = function(){
                $http.post("/task/",$scope.detalhe,{}).then(function(successResponse){
                    $scope.original = $scope.detalhe;
                    inform.add("Tarefa salva!",{ttl:1000,type:"success"});
                },function(failResponse){
                    inform.add("Erro ao salvar a tarefa!",{ttl:2000,type:"error"});
                });
            };

            $scope.complete = function(){
                $http.head("/task/complete",{params:{id:$scope.detalhe.idTask}}).then(function(successResponse){
                    $scope.lista.splice($scope.lista.indexOf($scope.detalhe),1);
                    $scope.detalhe=undefined;
                    $scope.original = $scope.detalhe;
                    inform.add("Tarefa Completada com sucesso!",{ttl:1000,type:"success"});
                },function(failResponse){
                    inform.add("Erro ao salvar a tarefa!",{ttl:2000,type:"error"});
                });
            };

            $scope.assignedTo = function(){
                if($scope.detalhe)
                    if($scope.detalhe.owner){
                        if($scope.detalhe.owner.name==$scope.usuarioCorrente.name) {
                            return 'Tarefa atribuída a você'
                        }else{
                            return 'Tarefa atribuída para '+$scope.detalhe.owner.name
                        }
                    }else{
                        return 'Tarefa sem atribuição';
                    }
            };

            $scope.createTask = function(request){
                var nova = {request:request};
                $http.post("/task/",nova,{}).then(function(successResponse){
                    $scope.lista.push(successResponse.data);
                    $scope.detalhe = successResponse.data;
                    $scope.original = angular.copy(successResponse.data);
                    $scope.modalWindow.close();
                    inform.add("Tarefa criada com sucesso!",{ttl:2000,type:"success"});
                },function(failResponse){
                    inform.add("Erro ao criar nova tarefa!",{ttl:2000,type:"error"});
                });
            };



            $scope.openDialog = function(item){
                    $scope.dialogWindow = $uibModal.open({
                        animation: true,
                        ariaLabelledBy: 'modal-title',
                        ariaDescribedBy: 'modal-body',
                        templateUrl: 'confirmDialog.html',
                        scope: $scope,
                        size: 'window',
                        backDrop: false,
                        controller: ["$scope", function ($scope) {
                            $scope.cancel = function () {
                                $scope.$parent.lista[$scope.$parent.lista.indexOf($scope.$parent.detalhe)] = $scope.$parent.original;
                                $scope.$parent.detalhe = $scope.$parent.original;
                                $scope.$parent.dialogWindow.close();
                                if(item) {
                                    $scope.$parent.select(item);
                                }else{
                                    $scope.$parent.openWindow();
                                }
                            };
                            $scope.save = function () {
                                $scope.$parent.dialogWindow.close();
                                $scope.$parent.save();
                                if(item) {
                                    $scope.$parent.select(item);
                                }else{
                                    $scope.$parent.openWindow();
                                }
                            };
                        }],
                        appendTo: angular.element("body")
                    });
            };



            $scope.openWindow = function(){
                if($scope.detalhe!=undefined && !angular.equals($scope.detalhe,$scope.original)){
                    $scope.openDialog(undefined);
                }else {
                    $scope.modalWindow = $uibModal.open({
                        animation: true,
                        ariaLabelledBy: 'modal-title',
                        ariaDescribedBy: 'modal-body',
                        templateUrl: 'novaTaskWindow.html',
                        scope: $scope,
                        size: 'window',
                        backDrop: false,
                        controller: ["$scope", function ($scope) {
                            $scope.request = undefined;

                            $scope.cancel = function () {
                                $scope.$parent.modalWindow.close();
                            };
                            $scope.save = function () {
                                $scope.$parent.createTask($scope.request);
                            };
                        }],
                        appendTo: angular.element("body")
                    });
                }

            }

            // inicializando

            $http.get("/task/init/",{}).then(function(successResponse){
                $scope.usuarioCorrente = successResponse.data.current;
                $scope.lista = successResponse.data.list;
            }, function(failResponse){
                $window.location="/index.html";
            });

        }]);
        app.filter("leftZeros",function(){
            return function(input,size){
                if(input) {
                    var s;
                    if ((typeof input) == "number") {
                        s = input.toString();
                    } else {
                        s = input;
                    }
                    var t = size - s.length;
                    if (t > 0)
                        for (x = 1; x <= t; x++)
                            s = "0" + s;
                    return s;
                }
            }
        });
        angular.bootstrap(document, ['app']);
    });
});