angular.module("gettext").run(['gettextCatalog', function (gettextCatalog) {
    gettextCatalog.setStrings('ru', {"Gettext test":"тест","Input text":"Введите текст","No translation":"","Welcome {{name}}!":"Привет {{name}}!","one task":"одна задача"});

}]);