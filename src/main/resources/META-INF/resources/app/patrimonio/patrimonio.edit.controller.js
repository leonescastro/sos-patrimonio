app.controller('PatrimonioFormController', PatrimonioFormController)

PatrimonioFormController.$inject = ['PatrimonioService', '$uibModalInstance', '$log', 'patrimonio', 'marcas'];
function PatrimonioFormController(PatrimonioService, $uibModalInstance, $log, patrimonio, marcas){
    var vm = this;
    
    vm.loading = false;
    vm.patrimonio = angular.copy(patrimonio);
    vm.marcas = angular.copy(marcas);
    
    vm.ok = function () {
      vm.loading = true;
      if (patrimonio.id) {
        PatrimonioService.update(vm.patrimonio.id, vm.patrimonio).then(function(success){
          $uibModalInstance.close(vm.patrimonio);
          vm.loading = false;
        }, function(error) {
          vm.loading = false;
          $log.error(error);
          alert(error.data.message);
        })
      } else {
        PatrimonioService.save(vm.patrimonio).then(function(success){
          $uibModalInstance.close(vm.patrimonio);
          vm.loading = false;
        }, function(error) {
          vm.loading = false;
          $log.error(error);
          alert(error.data.message);
        })
      }
    };
  
    vm.cancel = function () {
      $uibModalInstance.dismiss('cancel');
    };

}