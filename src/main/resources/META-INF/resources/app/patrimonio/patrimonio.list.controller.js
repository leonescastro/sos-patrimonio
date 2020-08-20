app.controller('PatrimonioController', PatrimonioController)

PatrimonioController.$inject = ['PatrimonioService', 'MarcaService', '$uibModal', '$log'];
function PatrimonioController(PatrimonioService, MarcaService, $uibModal, $log){
    var vm = this;

    vm.loading = true;
    vm.patrimonios = []
    vm.marcas = []

    MarcaService.findAll().then(function(response){
        vm.marcas = response.data;
    })

    vm.findAll = function () {
        PatrimonioService.findAll().then(function(response){
          vm.patrimonios = response.data;
          vm.loading = false;
        });    
    }
    
    vm.edit = function (patrimonio) {

        var modalInstance = $uibModal.open({
          templateUrl: 'modal.edit.html',
          controller: 'PatrimonioFormController',
          controllerAs: 'vm',
          resolve: {
            patrimonio: function () {
                return patrimonio ? patrimonio : {};
            },
            marcas: function () {
                return vm.marcas;
            }
          }
        });
    
        modalInstance.result.then(function (patrimonio) {
            $log.info("patrimonio : ", patrimonio);
            vm.loading = true;
            vm.findAll();
        }, function () {
          $log.info('Modal dismissed at: ' + new Date());
        });
    };

    vm.delete = function (id) {

        var modalInstance = $uibModal.open({
          templateUrl: 'modal.delete.html',
          controller: 'PatrimonioDeleteController',
          controllerAs: 'vm',
          resolve: {
            id: function () {
              return id;
            }
          }
        });
    
        modalInstance.result.then(function (id) {
            $log.info("Patrimonio deletado : ", id);
            vm.loading = true;
            vm.findAll();
        }, function () {
          $log.info('Modal dismissed at: ' + new Date());
        });
    };

    vm.findAll();

}