app.factory('PatrimonioService', PatrimonioService)

PatrimonioService.$inject = ['$http'];
function PatrimonioService($http){
    var vm = this;

    vm.findAll = function(){
        return $http.get("/patrimonios");
    }

    vm.findById = function(id){
        return $http.get("/patrimonios/id");
    }

    vm.delete = function(id){
        return $http.delete("/patrimonios/"+id);
    }

    vm.update = function(id, patrimonio){
        return $http.put("/patrimonios/"+id, { nome: patrimonio.nome, marca_id: patrimonio.marca.id});
    }

    vm.save = function(patrimonio){
        return $http.post("/patrimonios", { nome: patrimonio.nome, marca_id: patrimonio.marca.id});
    }

    return vm;
}