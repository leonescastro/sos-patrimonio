app.factory('MarcaService', MarcaService)

MarcaService.$inject = ['$http'];
function MarcaService($http){
    var vm = this;

    vm.findAll = function(){
        return $http.get("/marcas");
    }

    vm.findById = function(id){
        return $http.get("/marcas/id");
    }

    vm.delete = function(id){
        return $http.delete("/marcas/"+id);
    }

    vm.update = function(id, marca){
        return $http.put("/marcas/"+id, { nome: marca.nome});
    }

    vm.save = function(marca){
        return $http.post("/marcas", { nome: marca.nome});
    }

    return vm;
}