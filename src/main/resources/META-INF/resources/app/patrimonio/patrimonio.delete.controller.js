app.controller('PatrimonioDeleteController', PatrimonioDeleteController)

PatrimonioDeleteController.$inject = ['PatrimonioService', '$uibModalInstance', 'id'];
function PatrimonioDeleteController(PatrimonioService, $uibModalInstance, id){
    var vm = this;

    vm.loading = false;
    vm.id = angular.copy(id);
    
    vm.ok = function () {
      vm.loading = true;
      PatrimonioService.delete(vm.id).then(function(success){
        $uibModalInstance.close(vm.id);
        vm.loading = false;
      }, function(error) {
        vm.loading = false;
        alert(error.data.message);
      })
    };
  
    vm.cancel = function () {
      $uibModalInstance.dismiss('cancel');
    };

}