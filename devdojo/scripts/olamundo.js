var p = document.createElement('p');
var ola = "DevDojo";
p.textContent = ola;
document.body.appendChild(p);
//Tipos:
var num = 10;
var bool = true;
var str = "A beautiful message";
var str2 = 'A beautiful message';
var str3 = "" + str2;
console.log(str3);
//Função Void:
function func() {
}
//!== e === sempre preferir ao invés de != e ==
if (bool !== null) { }
if (bool === undefined) { }
if (bool) {
    var i = void 0;
    for (i = 0; i < 10; i++) {
        console.log(i);
    }
}
//Não funciona devido ao escopo da variável i declarada com let
//console.log("Hello " + i);
//Arrays
var list = [1, 2, 3];
var list2 = [1, 2, 3];
var tuple;
tuple = ["teste", 1];
console.log(tuple);
//ENUM
var Day;
(function (Day) {
    Day[Day["MONDAY"] = 0] = "MONDAY";
    Day[Day["TUESDAY"] = 1] = "TUESDAY";
})(Day || (Day = {}));
;
var day = Day.MONDAY;
console.log("" + day);
//Argumentos não obrigatórios
/*function add(v1:number, v2:number, v3?:number):number {
  if(v3 !== undefined)
    return v1+v2+v3;
  return v1+v2;
}

console.log(add(1,2));
console.log(add(1,2,3));*/
//Usar any para quando não sabemos o tipo do argumento
function add(v1, v2, v3) {
    if (v3 !== undefined)
        return v1 + v2 + v3;
    return v1 + v2;
}
console.log(add('hello', ' world'));
console.log(add(1, 2, 3));
var var1 = 'Teste';
//variáveis do tipo void só podem receber null ou undefined
var void1 = null;
var void2 = undefined;
//Casting
var stringLenght = var1.length;
var stringUpperCase = var1.toUpperCase();
console.log(stringLenght);
console.log(stringUpperCase);
//Rest Parameter
function add2(v1) {
    var v2 = [];
    for (var _i = 1; _i < arguments.length; _i++) {
        v2[_i - 1] = arguments[_i];
    }
    var sum = 0;
    for (var i = 0; i < v2.length; i++) {
        sum += v2[i];
    }
    console.log(v1 + sum);
}
add2("Sum is ", 1, 2, 3, 4);
//# sourceMappingURL=olamundo.js.map