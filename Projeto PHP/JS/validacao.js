/*Validação de nome, email e telefone*/
function validacao(){
  var nome = document.getElementById("nome");
  var email = document.getElementById("email");
  var telefone = document.getElementById("telefone");
  if(nome.value === ""){
    alert("O campo Nome não pode ser vazio!!");
    return false;
  }
  if(email.value === ""){
    alert("O campo Email não pode ser vazio!!");
    return false;
  }
  if(telefone.value === ""){
    alert("O campo telefone não pode ser vazio!!");
    return false;
  }
  /*Se for número de celular tem 15 chars, mas fixo tem um digito a menos*/
  if(telefone.value.length < 14){
    alert("O campo telefone está errado!");
    return false;
  }
  return true;
}

/*Mascara do telefone*/
document.getElementById('telefone').addEventListener('input', function(e){
  var x = e.target.value.replace(/\D/g, '').match(/(\d{0,2})(\d{0,5})(\d{0,4})/);
  e.target.value = !x[2] ? x[1] : '(' + x[1] + ') ' + x[2] + (x[3] ? '-' + x[3] : '');
});

/*Mascara para só pegar numero (para Quantidade e CEP)*/
document.getElementById('quantidade').addEventListener('input', function(e){
  var x = e.target.value.replace(/\D/g,'');
  e.target.value = e.target.value.match(x);
})

document.getElementById('CEP').addEventListener('input', function(e){
  var x = e.target.value.replace(/\D/g,'');
  e.target.value = e.target.value.match(x);
})

/*Mascara para UF*/
document.getElementById('UF').addEventListener('input', function(e){
  var x = e.target.value.replace(/\d/g,'');
  e.target.value = e.target.value.match(x);
  e.target.value = e.target.value.toUpperCase();
})
