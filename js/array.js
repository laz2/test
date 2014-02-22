describe('Array', function () {

	it('-> создает разреженный массив', function () {
		var a = [1,,, 5];
		expect(a.length).toBe(4);
		expect(a[1]).toBeUndefined();
		expect(a[2]).toBeUndefined();
	});

	it('-> создает пустой массив с выделением определенного количества памяти', function () {
		var a = new Array(10);
		expect(a.length).toBe(10);
		expect(a[0]).toBeUndefined();
		expect(a[9]).toBeUndefined();
	});

	it('-> создает массив через конструктор', function () {
		var a = new Array(1, 2, 3);
		expect(a.length).toBe(3);
		expect(a[0]).toBe(1);
		expect(a[1]).toBe(2);
		expect(a[2]).toBe(3);
	});

});
