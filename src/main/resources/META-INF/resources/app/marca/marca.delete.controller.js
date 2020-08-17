app.controller('MarcaDeleteController', MarcaDeleteController)

MarcaDeleteController.$inject = ['MarcaService', '$uibModalInstance', 'id'];
function MarcaDeleteController(MarcaService, $uibModalInstance, id){
    var vm = this;

    vm.loading = false;
    vm.id = angular.copy(id);
    
    vm.ok = function () {
      vm.loading = true;
      MarcaService.delete(vm.id).then(function(success){
        $uibModalInstance.close(vm.id);
        vm.loading = false;
      }, function(error) {
        vm.loading = false;
        $log.error(error);
        alert(error.data.message);
      })
    };
  
    vm.cancel = function () {
      $uibModalInstance.dismiss('cancel');
    };

}