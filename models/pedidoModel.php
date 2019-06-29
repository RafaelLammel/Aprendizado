<?php

/*Abrindo conexão com o banco de dados*/
require_once("../config/bd.php");
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

$to = $p->getEmail();
$subject = "Pedido cadastrado";
$message = "Seu pedido foi cadastrado com sucesso! :)";
$header = "From: rafaellmarinheiro42@gmail.com"."\r\n".
          "X=Mailer:PHP/".phpversion();

if(mail($to,$subject,$message,$header)){
  echo "Email enviado com sucesso!";
}

mysqli_close($conn);

 ?>
