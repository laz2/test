var app = angular.module('app', ['gettext'])
app.run(function (gettextCatalog) {
	gettextCatalog.currentLanguage = 'ru';
	gettextCatalog.debug = true;
});
app.controller('TestCtrl', function ($scope, gettextCatalog) {
	$scope.name = 'Petya';
	$scope.count = 1;
	$scope.langs = ['en', 'ru'];
	$scope.lang = gettextCatalog.currentLanguage;
	$scope.changeLang = function () {
		gettextCatalog.currentLanguage = $scope.lang;
	};
});