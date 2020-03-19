fetch('https://api.myjson.com/bins/oi83g').then( response => {
  if(response.status === 200) {
    response.json().then(function(livros) {
      livros.forEach(livro => {
        adicionaLivro(livro)
      });
    })
  }
  else{
    //Tratar Erro
  }
}).catch( err => {
  console.log("Erro ao buscar dados =( ", err)
  //Tratar Erro
})