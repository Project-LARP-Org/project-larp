'use strict';
angular.module('app') //

.controller('LoginController', function($scope, $rootScope, $auth, $location, $route, EnvService, $mdDialog) {
	// CONST
	var loginErrorMsg = 
			"Authentification Error: " +
			"please check your login, password." +
			"Then retry.";
    var errorDialog = $mdDialog.alert()
	    .parent(angular.element(document.querySelector('#popupContainer')))
	    .clickOutsideToClose(true)
	    .title('Sorry')
	    .textContent(loginErrorMsg)
	    .ok('Got it!');
	var progressDialog = {
            clickOutsideToClose: false,
            scope: $scope,        
            preserveScope: true,           
            template: 
'<div layout-padding><md-dialog>' +
'  <md-dialog-content>' +
'<div layout="row" layout-align="center center">' +
'  <div>' +
'    <md-progress-circular md-mode="indeterminate" class="md-hue-1" md-diameter="96"></md-progress-circular>' +
'  </div>' +
'</div>' +
'  </md-dialog-content>' +
'</md-dialog> </div>',
            controller: function DialogController($scope, $mdDialog) {
               $scope.closeDialog = function() {
                  //Nothing
               }
            }
         };
    
	// ACTIONS
	$scope.login = function() {
		$mdDialog.show(progressDialog);
		$auth.login(
			{
				username: $scope.username,
				password: $scope.password
			})
			.then(function(response) {
				$mdDialog.hide(progressDialog);
				$rootScope.notifyLogin();
				$location.url('/');
				$route.reload();
			})
			.catch(function(error) {
				$mdDialog.hide(progressDialog);
				$rootScope.notifyLogout();
				$mdDialog.show(errorDialog);
			});
	};
	// INIT
	if($auth.isAuthenticated()) {
		$location.url('/');
		$route.reload();
	}
	EnvService.get(function(response) {
		$scope.buildVersion = response.buildVersion;
		$scope.siteUrl = response.siteUrl;
	});
})

;