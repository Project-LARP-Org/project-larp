'use strict';

angular.module('app', [ //
	'ngResource',  'ngRoute', //
	'ngMaterial', 'ngAnimate', //
	'satellizer', //
	'md.data.table', //
	'nvd3', //
	'pascalprecht.translate', 'tmh.dynamicLocale', 'ngCookies', //
	'datePicker', //
	'templates-main' // GENERATED/AGGREGATED template.js
])

// CONFIG
.config(function($routeProvider, $locationProvider) {
	// ROUTE: URLs
	$routeProvider. //
		when('/home',        { templateUrl : 'js/modules/home/home.html', resolve : { loginRequired : loginRequired }	}).
		when('/about',       { templateUrl : 'js/modules/home/about.html'	}).
		when('/login',       { templateUrl : 'js/modules/auth/login.html'	}).
		when('/logout',      { templateUrl : 'js/modules/auth/logout.html' }).
		when('/help',        { templateUrl : 'js/modules/help/help.html', resolve : { loginRequired : loginRequired }	}).
		otherwise('/home');
	$locationProvider.hashPrefix('');
	//$locationProvider.html5Mode(true);
})

.config(function($authProvider) {
	$authProvider.withCredentials = true;
	$authProvider.tokenRoot = null;
	$authProvider.cordova = false;
	$authProvider.baseUrl = './api/';
	$authProvider.loginUrl = '/auth/login';
	$authProvider.signupUrl = '/auth/signup'; // TODO ???
	$authProvider.unlinkUrl = '/auth/unlink/'; // TODO ???
	$authProvider.tokenName = 'token';
	$authProvider.tokenPrefix = 'project_larp';
	$authProvider.authHeader = 'Authorization';
	$authProvider.authToken = 'Bearer';
	$authProvider.storageType = 'localStorage';
})

.config(function($mdIconProvider) {
	$mdIconProvider //
		.iconSet('action', 'angular/icons/sets/action-icons.svg', 24) //
		/*
		.iconSet('alert', 'angular/icons/sets/alert-icons.svg', 24) //
		.iconSet('av', 'angular/icons/sets/av-icons.svg', 24) //
		.iconSet('communication', 'angular/icons/sets/communication-icons.svg', 24) //
		.iconSet('content', 'angular/icons/sets/content-icons.svg', 24) //
		.iconSet('device', 'angular/icons/sets/device-icons.svg', 24) //
		.iconSet('editor', 'angular/icons/sets/editor-icons.svg', 24) //
		.iconSet('file', 'angular/icons/sets/file-icons.svg', 24) //
		.iconSet('hardware', 'angular/icons/sets/hardware-icons.svg', 24) //
		.iconSet('icons', 'angular/icons/sets/icons-icons.svg', 24) //
		.iconSet('image', 'angular/icons/sets/image-icons.svg', 24) //
		.iconSet('maps', 'angular/icons/sets/maps-icons.svg', 24) //
		.iconSet('navigation', 'angular/icons/sets/navigation-icons.svg', 24) //
		.iconSet('notification', 'angular/icons/sets/notification-icons.svg', 24) //
		.iconSet('social', 'angular/icons/sets/social-icons.svg', 24) //
		.iconSet('toggle', 'angular/icons/sets/toggle-icons.svg', 24) //
		.iconSet('mdi', 'angular/icons/sets/mdi-icons.svg', 24) //
		*/
		;
})

.config(function($mdThemingProvider) {
	$mdThemingProvider.theme('default') //
		.primaryPalette('brown') //
		.accentPalette('green') // 
		.warnPalette('red') //
		.backgroundPalette('grey') //
		;
	// INIT
	$mdThemingProvider.setDefaultTheme('default');
})

//i18n: LANGUAGE
.config( function ($translateProvider) {
	
  // ADD LANG
  $translateProvider
  .useStaticFilesLoader({
    prefix: 'i18n/locale-',
    suffix: '.json'
  })
  
  // REMEMBER
  .preferredLanguage('en')
  .fallbackLanguage('en')
  .useCookieStorage()
  
  // SECURITY
  .useSanitizeValueStrategy('escape');
})

/**
 * Helper auth functions
 * https://github.com/sahat/satellizer/blob/master/examples/client/app.js
 */
function skipIfLoggedIn($q, $auth) {
	var deferred = $q.defer();
	if ($auth.isAuthenticated()) {
		deferred.reject();
	} else {
		deferred.resolve();
	}
	return deferred.promise;
}

function loginRequired($q, $location, $auth, $route) {
	var deferred = $q.defer();
	if ($auth.isAuthenticated()) {
		deferred.resolve();
	} else {
		$location.url('/login');
		$route.reload();
	}
	return deferred.promise;
}


// disable aria warning (accessibility)
console.realWarn = console.warn;
console.warn = function(message) {
	if (message.indexOf("ARIA") == -1) {
		console.realWarn.apply(console, arguments);
	}
};
