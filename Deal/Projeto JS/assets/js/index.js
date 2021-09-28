var tabela = document.querySelector("#tabela-livros");

function adicionaLivro(livro) {
  let tr = adicionaTr(livro)
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

/*JS para pesquisa*/

var inputPesquisa = document.querySelector("#pesquisa-tabela");

inputPesquisa.addEventListener("keyup", () => {
  let filtro = inputPesquisa.value.toUpperCase();
  let tr = tabela.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    let td = tr[i].getElementsByTagName("td")[1];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filtro) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
})