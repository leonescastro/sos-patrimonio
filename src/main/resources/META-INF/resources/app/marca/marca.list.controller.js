app.controller('MarcaController', MarcaController)

MarcaController.$inject = ['MarcaService', '$uibModal', '$log'];
function MarcaController( MarcaService, $uibModal, $log){
    var vm = this;

    vm.loading = true;
    vm.marcas = []

    vm.findAll = function () {
      MarcaService.findAll().then(function(response){
          vm.marcas = response.data;
          vm.loading = false;
        });    
    }
    
    vm.edit = function (marca) {

        var modalInstance = $uibModal.open({
          templateUrl: 'modal.edit.html',
          controller: 'MarcaFormController',
          controllerAs: 'vm',
          resolve: {
            marca: function () {
                return marca;
            }
          }
        });
    
        modalInstance.result.then(function (marca) {
            $log.info("marca : ", marca);
            vm.loading = true;
            vm.findAll();
        }, function () {
          $log.info('Modal dismissed at: ' + new Date());
        });
    };

    vm.delete = function (id) {

        var modalInstance = $uibModal.open({
          templateUrl: 'modal.delete.html',
          controller: 'MarcaDeleteController',
          controllerAs: 'vm',
          resolve: {
            id: function () {
              return id;
            }
          }
        });
    
        modalInstance.result.then(function (id) {
            $log.info("Marca deletada : ", id);
            vm.loading = true;
            vm.findAll();
        }, function () {
          $log.info('Modal dismissed at: ' + new Date());
        });
    };

    vm.findAll();

}