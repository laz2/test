describe("function", function () {

    it("-> по умолчанию возвращает undefined", function () {
        expect((function () {
        })()).toBeUndefined();
    });

    it("-> анонимная функция с именем, имя доступно только внутри функции", function () {
        var f = function fn() {
            expect(fn).toBeDefined();
        };
        f();
        expect(function () {
            fn();
        }).toThrow('fn is not defined');
    });

    it("-> this в strict-режиме является undefined", function () {
        "use strict";
        var self = (function () {
            return this;
        })();
        expect(self).toBeUndefined();
    });

    it("-> this в non-strict-режиме является window", function () {
        var self = (function () {
            return this;
        })();
        expect(self).toBe(window);
    });

    it("-> может быть вызвана с меньшим количеством аргументов, чем объявлена", function () {
        (function (a1, a2, a3, a4) {
            expect(a1).toBe(1);
            expect(a2).toBe(2);
            expect(a3).toBeUndefined();
            expect(a4).toBeUndefined();
        })(1, 2);
    });

    it("-> может быть вызвана с большим количеством аргументов, чем объявлена", function () {
        (function (a1, a2, a3, a4) {
        })(1, 2, 3, 4, 5);
    });

    describe("-> arguments", function () {
        it("-> элемент является ссылкой на переменную параметра", function () {
            var fn = function (a) {
                arguments[0] = 4;
                expect(a).toBe(arguments[0]);

                a = 5;
                expect(arguments[0]).toBe(a);
            };

            fn(1);
        });

        it("-> не является объектом типа Array", function () {
            expect(arguments instanceof Array).toBeFalsy();
            expect(arguments.prototype).toBeUndefined();
            expect(arguments.slice).toBeUndefined();
            expect(arguments.splice).toBeUndefined();
        });

        it("-> имеет свойство callee", function () {
            var fn = function () {
                expect(arguments.callee).toBeDefined();
                expect(arguments.callee).toBe(fn);
            };
            fn();
        });

        it("-> не имеет свойства caller, потому что deprecated", function () {
            var fn = function () {
                (function () {
                    expect(arguments.caller).toBeUndefined();
                })();
            };
            fn();
        });
    });

    it("-> может иметь свойства", function () {
        var fn = function () {
            expect(arguments.callee.prop).toBe(1);
        };
        fn.prop = 1;
        fn();
    });

    describe("-> замыкание", function () {
        it("-> лексическая область видимости", function () {
            var fn = function (b) {
                var a = 1;
                var fn = function () {
                    return a;
                };
                a = b;
                return fn;
            };

            expect(fn(null)()).toBeNull();
            expect(fn(1)()).toBe(1);
        });
    });
});
