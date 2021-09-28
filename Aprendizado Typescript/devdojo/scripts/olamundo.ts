let p = document.createElement('p');
let ola: string = "DevDojo";
p.textContent = ola;
document.body.appendChild(p);

//Tipos:

let num:number = 10;
let bool:boolean = true;
let str:string = "A beautiful message";
let str2:string = 'A beautiful message';
let str3:string = `${str2}`;
console.log(str3);

//Função Void:
function func():void {

}

//!== e === sempre preferir ao invés de != e ==
if(bool !== null) {}
if(bool === undefined) {}

if(bool) {
  let i:number;
  for(i = 0; i < 10; i++) {
    console.log(i);
  }
}

//Não funciona devido ao escopo da variável i declarada com let
//console.log("Hello " + i);

//Arrays
let list:number[] = [1, 2, 3];
let list2:Array<number> = [1, 2, 3];

let tuple:[string, number];
tuple = ["teste", 1];
console.log(tuple);

//ENUM
enum Day{MONDAY, TUESDAY};

let day:Day = Day.MONDAY;

console.log(`${day}`);

//Argumentos não obrigatórios
/*function add(v1:number, v2:number, v3?:number):number {
  if(v3 !== undefined)
    return v1+v2+v3;
  return v1+v2;
}

console.log(add(1,2));
console.log(add(1,2,3));*/

//Usar any para quando não sabemos o tipo do argumento
function add(v1:any, v2:any, v3?:any):any {
  if(v3 !== undefined)
    return v1+v2+v3;
  return v1+v2;
}

console.log(add('hello',' world'));
console.log(add(1,2,3));

let var1:any = 'Teste';

//variáveis do tipo void só podem receber null ou undefined
let void1:void = null;
let void2:void = undefined;

//Casting
let stringLenght:number = (<string> var1).length;
let stringUpperCase:string = (var1 as string).toUpperCase();

console.log(stringLenght);
console.log(stringUpperCase);

//Rest Parameter
function add2(v1:string, ...v2:number[]):void {
  let sum:number = 0;
  for(let i = 0; i < v2.length; i++) {
    sum += v2[i];
  }
  console.log(v1 + sum);
}

add2("Sum is ", 1, 2, 3, 4);