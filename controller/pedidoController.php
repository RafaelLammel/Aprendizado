<?php

session_start();

/*Abrindo conexão com o banco de dados*/
require_once realpath(dirname(__FILE__).'/..').'/bd.php';

/*Pegando a classe Pedido*/
require realpath(dirname(__FILE__).'/..')."/classes/pedido.php";

/*Se for TRUE, muda alguns valores de texto da página de cadastro*/
$editar = false;
/*Armazena o ID que requisitou alteração no cadastro*/
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

  $_SESSION['message'] = "Cadastro realizado com sucesso!";

  /*Essa função foi testada pelo XAMPP, alterando algumas configurações,
  seguindo este guia: https://stackoverflow.com/questions/15965376/how-to-configure-xampp-to-send-mail-from-localhost*/
  $to = $p->getEmail().", davi@signoweb.com.br";
  $subject = "Confirmação de Pedido";
  $message = "Seu pedido foi cadastrado e confirmado.";
  if(mail($to,$subject,$message))
    echo "Email enviado com sucesso.";
  else {
    echo "Falha ao enviar email de confirmação.";
  }

  /*Liberando a variavel $p e voltando para a tela inicial de cadastro*/
  unset($p);
  header("location: ../index.php");

  /*Fechando a conexão com o banco de dados*/
  mysqli_close($conn);

}

/*Verificando se o usuário está vindo a partir do botão editar da pagina
Pedidos Cadastrados*/
if(isset($_GET['edit'])){

  /*Pegando o ID de quem pediu alteração*/
  $id = $_GET['edit'];
  $editar = true;

  /*SELECT busca o cadastro no banco de dados baseado no ID*/
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

  /*Fechando a conexão com o banco de dados*/
  mysqli_close($conn);

}

/*Função para gravar as alterações no banco de dados*/
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

  /*Query para a alteração do cadastro no banco de dados*/
  $conn->query("UPDATE pedido SET nome = '{$p->getNome()}', endereco = '{$p->getEndereco()}',
  bairro = '{$p->getBairro()}',CEP = '{$p->getCEP()}',cidade = '{$p->getCidade()}',UF = '{$p->getUF()}',
  email = '{$p->getEmail()}', telefone = '{$p->getTelefone()}',revistinha = '{$p->getRevistinha()}',
  quantidade = '{$p->getQuantidade()}',atracoes = '{$p->getAtracoes()}',sugestao = '{$p->getSugestao()}',
  imagem = '{$p->getImagem()}' WHERE ID = '$id'");

  $_SESSION['message'] = "Alterações feitas com sucesso!";
  header('location: ../index.php');
}

/*Checa se o pedido veio de deletar*/
if(isset($_GET['delete'])){

  /*Pegando o ID passado por parametro*/
  $id = $_GET['delete'];

  /*Query para o banco de dados deletar o pedido pelo ID*/
  $conn->query("DELETE FROM pedido WHERE ID = '$id'");

  $_SESSION['message'] = "Pedido deletado com sucesso!";

}

if (isset($_POST["export"])) {
  $productResult = $conn->query("SELECT * FROM pedido");
    $filename = "Export_excel.xls";
    header("Content-Type: application/vnd.ms-excel");
    header("Content-Disposition: attachment; filename=\"$filename\"");
    $isPrintHeader = false;
    if (! empty($productResult)) {
        foreach ($productResult as $row) {
            if (! $isPrintHeader) {
                echo implode("\t", array_keys($row)) . "\n";
                $isPrintHeader = true;
            }
            echo implode("\t", array_values($row)) . "\n";
        }
    }
    exit();
}

?>
