app.controller("AppController", AppController);

AppController.$inject = ['$state'];
function AppController($state){
  var vm = this;

  vm.tabs = [
    { name: 'patrimonios', heading: "Patrimonios", route: "tabs.patrimonios", active: false, isVisible: true, href: $state.href("tabs.patrimonios") },
    { name: 'marcas', heading: "Marcas", route: "tabs.marcas", active: false, isVisible: true, href: $state.href("tabs.marcas") }
];

  vm.tabSelected = function( route ) {
    console.log('route : ', route );
    $state.go(route);
  }

};