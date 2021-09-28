function printName(person:{name:string}):void {
  console.log(person.name);
}

let randomObj = {name: "William", age: 20};
let randomObj2 = {age: 20};
printName(randomObj);
//Erro de compilação, pois definimos que o parametro espera uma string com nome "name"
//printName(randomObj2);

interface Person {
  name:string;
}

interface Employee extends Person {
  salary:number;
}

let pe:Person = {name: "William"};
let emp:Employee = {name: "William", salary: 2000};

printName(pe);
printName(emp);

function printName2(person:Person):void {
  console.log(person.name);
}

printName2(pe);
printName2(emp);
printName2(randomObj);
printName2({name: "William"});

interface Person {
  age?:number;
}

interface Manager extends Employee {
  readonly bonus:number;
}

let manager:Manager = {name: "William", salary: 2000, bonus:200};
manager.name = "Alterado";
printName2(manager);