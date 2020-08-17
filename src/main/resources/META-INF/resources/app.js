
var app = angular
            .module('sosPatrimonioApp', [
              'ngAnimate', 
              'ui.bootstrap', 
              'ngSanitize', 
              'ui.select',
              'ui.router'
            ]);


app.config(AppConfig);

AppConfig.$inject = ['$uibModalProvider', '$locationProvider', '$stateProvider', '$urlRouterProvider'];
function AppConfig($uibModalProvider, $locationProvider, $stateProvider, $urlRouterProvider) {
  $uibModalProvider.options = { animation: true, backdrop: 'static', keyboard: false };
  $locationProvider.html5Mode(false);

  $urlRouterProvider
      .when('/', '/patrimonios')
      .otherwise("/patrimonios");
      
  $stateProvider
      .state('tabs', {
          abstract: true,
          url: '/',
          views: {
              'tabs': {
                  controller: 'AppController as vm',
                  templateUrl: 'app.tpl.html',
              }
          }
      })
      .state('tabs.patrimonios', {
          url: 'patrimonios',
          templateUrl: '/app/patrimonio/patrimonio.list.tpl.html',
          controller: 'PatrimonioController as vm',
          reloadOnSearch: false
      })
      .state('tabs.marcas', {
          url: 'marcas',
          templateUrl: '/app/marca/marca.list.tpl.html',
          controller: 'MarcaController as vm',
          reloadOnSearch: false
      })

}

AppRun.$inject = ['$log'];
app.run(AppRun);
function AppRun($log, navigationService) {
  // Note, we need a reference to the navigationService so $state events are tracked.
  $log.log("Start.");
}
