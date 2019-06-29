<?php

/*Checando se ele veio do botão de submit pra impedir de criar um cadastro
cheio de atributos NULLs*/
if(isset($_POST['continuar'])){

/*Se o usuário recarregar a página, ele não vai refazer as operações*/
$_POST['continuar'] = NULL;

/*Abrindo conexão com o banco de dados*/
require_once("../bd.php");

/*Pegando a classe Pedido*/
require("../classes/pedido.php");

/*Verificando se o botão de sugestão foi ou não pressionado (Boolean)*/
if(isset($_POST['sugestao'])){
  $sugestao = TRUE;
}
else{
  $sugestao = FALSE;
}

/*Verificando se não foi marcado nenhum tipo de revistinha, no caso coloca NULL*/
if(!isset($_POST['revistinha'])){
  $revistinha = NULL;
}
else {
  $revistinha = $_POST['revistinha'];
}

/*Criando e preenchendo uma instancia de Pedido com o construtor*/
$p = new Pedido($_POST['nome'],
                $_POST['endereco'],
                $_POST['bairro'],
                $_POST['CEP'],
                $_POST['cidade'],
                $_POST['UF'],
                $_POST['email'],
                $_POST['telefone'],
                $revistinha,
                $_POST['quantidade'],
                $_POST['atracoes'],
                $sugestao,
                $_POST['imagens']);

/*Query para inserir os dados no banco*/
$conn->query("INSERT INTO pedido(nome,endereco,bairro,CEP,cidade,UF,email,
telefone,revistinha,quantidade,atracoes,sugestao,imagem)
VALUES ('{$p->getNome()}','{$p->getEndereco()}','{$p->getBairro()}','{$p->getCEP()}',
'{$p->getCidade()}','{$p->getUF()}','{$p->getEmail()}','{$p->getTelefone()}',
'{$p->getRevistinha()}','{$p->getQuantidade()}','{$p->getAtracoes()}',
'{$p->getSugestao()}','{$p->getImagem()}')");

/*Fechando a conexão com o banco de dados*/
mysqli_close($conn);

?>
<html>
  <head>
    <meta charset="utf-8">
    <link rel="stylesheet" type="text/css" href="../styles/style.css">
    <style>
      .wrapper{
        display: flex;
        justify-content: center;
      }
    </style>
    <title>Cadastro de Pedidos</title>
  </head>
  <body>
    <section class="content">
      <div class="container">
        <h3>Cadastro efetuado com sucesso!</h3>
        <div class = "wrapper">
        <button onclick="loadIndex()">Voltar</button>
        </div>
      </div>
    </section>
    <script>
      function loadIndex(){
        location.assign("../index.html");
      }
    </script>
  </body>
</html>

<?php

}

else{
?>
  <html>
    <head>
      <meta charset="utf-8">
      <link rel="stylesheet" type="text/css" href="../styles/style.css">
      <style>
        .wrapper{
          display: flex;
          justify-content: center;
        }
      </style>
      <title>Cadastro de Pedidos</title>
    </head>
    <body>
      <section class="content">
        <div class="container">
          <h3>Você não deveria estar aqui!</h3>
          <div class = "wrapper">
          <button onclick="loadIndex()">Voltar</button>
          </div>
        </div>
      </section>
      <script>
        function loadIndex(){
          location.assign("../index.html");
        }
      </script>
    </body>
  </html>
<?php
}

 ?>
