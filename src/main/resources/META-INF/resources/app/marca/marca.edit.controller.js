app.controller('MarcaFormController', MarcaFormController)

MarcaFormController.$inject = ['MarcaService', '$uibModalInstance', '$log', 'marca'];
function MarcaFormController(MarcaService, $uibModalInstance, $log, marca){
    var vm = this;
    vm.loading = false;

    vm.marca = angular.copy(marca);
    
    vm.ok = function () {
      vm.loading = true;
      if (vm.marca.id) {
        MarcaService.update(vm.marca.id, vm.marca).then(function(success){
          $uibModalInstance.close(vm.marca);
          vm.loading = false;
        }, function(error) {
          vm.loading = false;
          $log.error(error);
          alert(error.data.message);
        })
      } else {
        MarcaService.save(vm.marca).then(function(success){
          $uibModalInstance.close(vm.marca);
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