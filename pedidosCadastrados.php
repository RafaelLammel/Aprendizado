<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" type="text/css" href="styles/style.css">
  <title>Lista de Pedidos</title>
</head>
<body>
  <?php
    require_once 'bd.php';
    $result = $conn->query("SELECT id, nome, telefone FROM pedido") or die($conn->error);
  ?>
  <div class = "menu">
    <ul>
      <li><a href="index.html">CADASTRAR PEDIDO</a></li>
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
        </tr>
        <?php
          while ($row = $result->fetch_assoc()):
        ?>
        <tr>
          <td><?php echo $row['id'];?></td>
          <td><?php echo $row['nome'];?></td>
          <td><?php echo $row['telefone'];?></td>
        </tr>
        <?php endwhile; ?>
      </table>
    </div>
  </section>
</body>
</html>
