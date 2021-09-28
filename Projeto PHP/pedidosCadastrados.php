<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="styles/style.css">
  <title>Lista de Pedidos</title>
</head>
<body>
  <?php require_once 'controller/pedidoController.php'; ?>
  <?php
    $result = $conn->query("SELECT id, nome, telefone FROM pedido") or die($conn->error);
  ?>
  <div class = "menu">
    <ul>
      <li><a href="index.php">CADASTRAR PEDIDO</a></li>
      <li><a href="pedidosCadastrados.php">VER PEDIDOS CADASTRADOS</a></li>
    </ul>
  </div>
  <section class="content">
    <div class="container">
      <table>
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>Telefone</th>
          <th>Editar</th>
          <th>Deletar</th>
        </tr>
        <?php while ($row = $result->fetch_assoc()): ?>
        <tr>
          <td><?php echo $row['id'];?></td>
          <td><?php echo $row['nome'];?></td>
          <td><?php echo $row['telefone'];?></td>
          <td>
            <a href="index.php?edit=<?php echo $row['id']; ?>">Editar</a>
          </td>
          <td>
            <a href="pedidosCadastrados.php?delete=<?php echo $row['id'] ?>">Deletar</a>
          </td>
        </tr>
        <?php endwhile; ?>
      </table>
      <form class="form" method="POST" action="controller/pedidoController.php">
        <div style="margin-bottom: 0px; text-align:center; margin-top:20px">
          <button type="submit" name="export" value="export">Exportar dados</button>
        </div>
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
  <?php if(isset($_SESSION['message'])):?>
    <script type="text/javascript">myFunction()</script>
  <?php unset($_SESSION['message']); endif ?>
</body>
</html>
