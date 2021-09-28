var Student = /** @class */ (function () {
    function Student(firstName, middleInitial, secondName) {
        this.firstName = firstName;
        this.middleInitial = middleInitial;
        this.secondName = secondName;
        this.fullName = firstName + " " + middleInitial + " " + secondName;
    }
    return Student;
}());
function greeter(person) {
    return "Hello, " + person.firstName + " " + person.secondName;
}
var user = new Student("Rafael", "L.", "Marinheiro");
document.body.textContent = greeter(user);
