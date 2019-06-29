<?php
/*Conectando ao BD*/
/* Variaveis para a conexão com o BD*/
$host = "localhost";
$user = "root";
$password = "";
$database = "bd_signoweb";

/*Variavel que tenta a conexão com o BD*/
$conn = new mysqli($host,$user,$password,$database);

/*Checar a conexão*/
if($conn->connect_error)
  die("Conexão falhou! " . $conn->connect_error);

 ?>
