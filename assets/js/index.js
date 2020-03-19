function adicionaLivro(livro) {
  let tr = adicionaTr(livro)
  var tabela = document.querySelector("#tabela-livros");
  tabela.appendChild(tr)
}

function adicionaTr(livro) {
  
  //Convertendo objeto para os dados que interessam
  let data = {
    codigo: livro.codigo,
    nome: livro.nome,
    preco: livro.preco,
    paginas: livro.paginas,
    categoria: livro.categoria.nome
  }
  
  //Criando uma nova tr
  let novaTr = document.createElement("tr");

  //Iterando sobre os dados e fazendo de cada um uma coluna
  for(let key in data) {
    let novaTd = document.createElement("td");
    novaTd.textContent = data[key]
    novaTr.appendChild(novaTd);
  }

  return novaTr;
}