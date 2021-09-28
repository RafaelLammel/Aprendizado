class Student {

  fullName: string;

  constructor(public firstName: string, public middleInitial: string, public secondName: string,) {
    this.fullName = firstName + " " + middleInitial + " " + secondName;
  }

}

interface Person {
  firstName: string;
  secondName: string;
}

function greeter(person: Person) {
  return "Hello, " + person.firstName + " " + person.secondName;
}

let user = new Student("Rafael","L.","Marinheiro");

document.body.textContent = greeter(user);