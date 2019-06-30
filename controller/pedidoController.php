<?php

session_start();

/*Abrindo conexão com o banco de dados*/
require_once realpath(dirname(__FILE__).'/..').'/bd.php';

/*Pegando a classe Pedido*/
require realpath(dirname(__FILE__).'/..')."/classes/pedido.php";

$editar = false;
$id = 0;

/*Checando se o usuário veio do botão de cadastro para fazer a inserção*/
if(isset($_POST['cadastrar'])){

  /*Se o usuário recarregar a página, ele não vai refazer as operações*/
  $_POST['continuar'] = NULL;

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

  unset($p);

  $_SESSION['message'] = "Cadastro realizado com sucesso!";

  header("location: ../index.php");

}

/*Verificando se o usuário está vindo a partir do botão editar*/
if(isset($_GET['edit'])){
  $id = $_GET['edit'];
  $editar = true;
  $result = $conn->query("SELECT * FROM pedido WHERE ID = $id") or die($conn->error());
  if($result!=NULL){
    $row = $result->fetch_array();
    $p = new Pedido($row['nome'],
                    $row['endereco'],
                    $row['bairro'],
                    $row['CEP'],
                    $row['cidade'],
                    $row['UF'],
                    $row['email'],
                    $row['telefone'],
                    $row['revistinha'],
                    $row['quantidade'],
                    $row['atracoes'],
                    $row['sugestao'],
                    $row['imagem']);
  }

}

if(isset($_POST['update'])){
  $id = $_POST['id'];

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

  $conn->query("UPDATE pedido SET nome = '{$p->getNome()}', endereco = '{$p->getEndereco()}',
  bairro = '{$p->getBairro()}',CEP = '{$p->getCEP()}',cidade = '{$p->getCidade()}',UF = '{$p->getUF()}',
  email = '{$p->getEmail()}', telefone = '{$p->getTelefone()}',revistinha = '{$p->getRevistinha()}',
  quantidade = '{$p->getQuantidade()}',atracoes = '{$p->getAtracoes()}',sugestao = '{$p->getSugestao()}',
  imagem = '{$p->getImagem()}' WHERE ID = '$id'");
  $_SESSION['message'] = "Alterações feitas com sucesso!";
  header('location: ../index.php');
}

mysqli_close($conn);

?>
