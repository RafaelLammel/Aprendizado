<?php

/*Classe representando o pedido*/
class Pedido{

  private $nome;
  private $endereco;
  private $bairro;
  private $CEP;
  private $cidade;
  private $UF;
  private $email;
  private $telefone;
  private $revistinha;
  private $quantidade;
  private $atracoes;
  private $sugestao;
  private $imagem;

  function Pedido($nome,$endereco,$bairro,$CEP,$cidade,$UF,$email,
  $telefone,$revistinha,$quantidade,$atracoes,$sugestao,$imagem){
    $this->nome = $nome;
    $this->endereco = $endereco;
    $this->bairro = $bairro;
    $this->CEP = $CEP;
    $this->cidade = $cidade;
    $this->UF = $UF;
    $this->email = $email;
    $this->telefone = $telefone;
    $this->revistinha = $revistinha;
    $this->quantidade = (int)$quantidade;
    $this->atracoes = $atracoes;
    $this->sugestao = $sugestao;
    $this->imagem = $imagem;
  }

  /*Getters e Setters (Setters não são utilizados no projeto)*/

  public function getNome()
  {
    return $this->nome;
  }

  public function setNome($nome)
  {
      $this->nome = $nome;
  }

  public function getEndereco()
  {
    return $this->endereco;
  }

  public function setEndereco($endereco)
  {
      $this->endereco = $endereco;
  }

  public function getBairro()
  {
    return $this->bairro;
  }

  public function setBairro($bairro)
  {
      $this->bairro = $bairro;
  }

  public function getCEP()
  {
    return $this->CEP;
  }

  public function setCEP($CEP)
  {
      $this->CEP = $CEP;
  }

  public function getCidade()
  {
    return $this->cidade;
  }

  public function setCidade($cidade)
  {
      $this->cidade = $cidade;
  }

  public function getUF()
  {
    return $this->UF;
  }

  public function setUF($UF)
  {
      $this->UF = $UF;
  }

  public function getEmail()
  {
    return $this->email;
  }

  public function setEmail($email)
  {
      $this->email = $email;
  }

  public function getTelefone()
  {
    return $this->telefone;
  }

  public function setTelefone($telefone)
  {
      $this->telefone = $telefone;
  }

  public function getRevistinha()
  {
    return $this->revistinha;
  }

  public function setRevistinha($revistinha)
  {
      $this->revistinha = $revistinha;
  }

  public function getQuantidade()
  {
    return $this->quantidade;
  }

  public function setQuantidade($quantidade)
  {
      $this->quantidade = $quantidade;
  }

  public function getAtracoes()
  {
    return $this->atracoes;
  }

  public function setAtracoes($atracoes)
  {
      $this->atracoes = $atracoes;
  }

  public function getSugestao()
  {
    return $this->sugestao;
  }

  public function setSugestao($sugestao)
  {
      $this->sugestao = $sugestao;
  }

  public function getImagem()
  {
    return $this->imagem;
  }

  public function setImagem($imagem)
  {
      $this->imagem = $imagem;
  }

}

?>
