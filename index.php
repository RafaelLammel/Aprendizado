<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Decidi deixar o HTML e CSS no mesmo arquivo -->
  <style>
    body{
      font-family: Arial;
      background: linear-gradient(to top left, #006600 26%, #0066ff 89%);
    }
    h3{
      text-align: center;
    }
    input{
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #DDD;
      border-radius: 5px;
      font-family: sans-serif;
      font-size: 100%;
    }
    button{
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #DDD;
      border-radius: 5px;
      font-family: sans-serif;
      font-size: 100%;
      background-color: darkgreen;
      color:white;
    }
    textarea{
      height: 150px;
      padding: 10px;
      margin-bottom: 15px;
      border: 1px solid #DDD;
      border-radius: 5px;
      font-family: sans-serif;
      font-size: 100%;
    }
    .content{
      display: flex;
      justify-content: center;
    }
    .container{
      width: 100%;
      max-width: 500px;
      background-color: rgba(255,255,255,0.8);
      padding: 15px;
      border-radius: 5px;
    }
    .form{
      display: flex;
      flex-direction: column;
    }
  </style>
  <title>Cadastro</title>
</head>
<body>
  <section class="content">
    <div class="container">
      <form onsubmit="return validacao()" class="form" method="POST" action="models/pedidoModel.php">
        <h3>DADOS PARA ENTREGA</h3>
        Nome:<input type="text" id="nome" name="nome">
        Endereço: <input type="text" name="endereco">
        Bairro: <input type="text" name="bairro">
        CEP: <input type="text" id="CEP" name="CEP" maxlength="8">
        Cidade: <input type="text" name="cidade">
        UF: <input type="text" id="UF" name="UF" maxlength="2">
        E-mail: <input type="email" id="email" name="email"
        placeholder="Ex.: fulano@email.com">
        Telefone: <input type="text" id="telefone" name="telefone"
        placeholder="Ex.: (41)99999-9999">
        <h3>DADOS PARA A PRODUÇÃO</h3>
        <div>
          Tipo Revistinha:
          <input type="radio" name="revistinha" value="Convite">Convite
          <input type="radio" name="revistinha" value="Lembrança">Lembrança
          <input type="radio" name="revistinha" value="Convite-Lembrança"
          >Convite-Lembrança
        </div>
        <br>
        <div>
          Quantidade: <input type="text" id="quantidade" name="quantidade">
        </div>
        Atrações do evento:
        <textarea name="atracoes" style="resize:none;"></textarea>
        <div style="display: flex; justify-content: center; font-size:70%">
        <input type="checkbox" name="sugestao">
        Aceito sugestões de texto para a capa
        </div>
        <br>
        <div>
        Imagens: <input type="file" name="imagens" accept="image/*"><br>
        </div>
        <br>
        <button type="submit" name="continuar" value="continuar">Continuar</button>
      </form>
    </div>
  </section>
  <script src="JS/validacao.js"></script>
</body>
</html>
