<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="styles/style.css">
  <title>Cadastro de Pedidos</title>
</head>
<body>
  <?php
    require_once "controller/pedidoController.php";
  ?>
  <div class = "menu">
    <ul>
      <li><a href="index.php">CADASTRAR PEDIDO</a></li>
      <li><a href="pedidosCadastrados.php">VER PEDIDOS CADASTRADOS</a></li>
    </ul>
  </div>
  <section class="content">
    <div class="container">
      <form onsubmit="return validacao()" class="form" method="POST" action="controller/pedidoController.php">
        <h3>DADOS PARA ENTREGA</h3>
        <input type="hidden" name="id" value=<?php echo $id; ?>>
        Nome:<input type="text" id="nome" name="nome" value="<?php if(isset($p)){echo $p->getNome();} ?>">
        Endereço: <input type="text" name="endereco" placeholder="Ex.: Rua Teste, 21"
        value="<?php if(isset($p)){echo $p->getEndereco();} ?>">
        Bairro: <input type="text" name="bairro" value="<?php if(isset($p)){echo $p->getBairro();} ?>">
        CEP: <input type="text" id="CEP" name="CEP" maxlength="8" value="<?php if(isset($p)){echo $p->getCEP();} ?>">
        Cidade: <input type="text" name="cidade" value="<?php if(isset($p)){echo $p->getCidade();} ?>">
        UF: <input type="text" id="UF" name="UF" maxlength="2" value="<?php if(isset($p)){echo $p->getUF();} ?>">
        E-mail: <input type="email" id="email" name="email"
        placeholder="Ex.: fulano@email.com" value="<?php if(isset($p)){echo $p->getEmail();} ?>">
        Telefone: <input type="text" id="telefone" name="telefone"
        placeholder="Ex.: (41)99999-9999" value="<?php if(isset($p)){echo $p->getTelefone();} ?>">
        <h3>DADOS PARA A PRODUÇÃO</h3>
        <div>
          Tipo Revistinha:
          <input type="radio" name="revistinha" value="Convite" <?php
                                                                $x = "";
                                                                if(isset($p))
                                                                  if($p->getRevistinha() == "Convite")
                                                                    $x = "checked";
                                                                echo $x;
                                                                ?>>Convite
          <input type="radio" name="revistinha" value="Lembrança" <?php
                                                                  $x = "";
                                                                  if(isset($p))
                                                                    if($p->getRevistinha() == "Lembrança")
                                                                      $x = "checked";
                                                                  echo $x;
                                                                  ?>>Lembrança
          <input type="radio" name="revistinha" value="Convite-Lembrança" <?php
                                                                          $x = "";
                                                                          if(isset($p))
                                                                            if($p->getRevistinha() == "Convite-Lembrança")
                                                                              $x = "checked";
                                                                          echo $x;
                                                                          ?>>Convite-Lembrança
        </div>
        <br>
        <div>
          Quantidade: <input type="text" id="quantidade" name="quantidade" value="<?php if(isset($p)){echo $p->getQuantidade();} ?>">
        </div>
        Atrações do evento:
        <textarea name="atracoes" style="resize:none;"><?php if(isset($p)){echo $p->getAtracoes();} ?></textarea>
        <div style="display: flex; justify-content: center; font-size:70%">
        <input type="checkbox" name="sugestao" <?php
                                                $x = "";
                                                if(isset($p))
                                                  if($p->getSugestao() == true)
                                                    $x = "checked";
                                                echo $x;
                                                ?>>
        Aceito sugestões de texto para a capa
        </div>
        <br>
        <div>
        Imagens: <input type="file" name="imagens" accept="image/*"><br>
        </div>
        <br>
        <?php if($editar == true): ?>
          <button type="submit" name="update" value="update">Salvar Mudanças</button>
        <?php else: ?>
          <button type="submit" name="cadastrar" value="continuar">Continuar</button>
        <?php endif; ?>
      </form>
    </div>
  </section>
  <div id="snackbar"><?php echo $_SESSION['message'];?></div>
  <script>
    function myFunction() {
      /*Código abaixo pra fazer a mensagem de confirmação da tela aparecer e sumir,
      veio da W3Schools: https://www.w3schools.com/howto/howto_js_snackbar.asp*/

      // Get the snackbar DIV
      var x = document.getElementById("snackbar");

      // Add the "show" class to DIV
      x.className = "show";

      // After 3 seconds, remove the show class from DIV
      setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);
      }
  </script>
  <script src="JS/validacao.js"></script>
  <?php if(isset($_SESSION['message'])):?>
    <script type="text/javascript">myFunction()</script>
  <?php unset($_SESSION['message']); endif ?>
</body>
</html>
