CREATE DATABASE bd_signoweb DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE bd_signoweb;

CREATE TABLE pedido(
  ID INT AUTO_INCREMENT PRIMARY KEY,
  nome VARCHAR(45),
  endereco VARCHAR(100),
  bairro VARCHAR(45),
  CEP VARCHAR(8),
  cidade VARCHAR(45),
  UF VARCHAR(2),
  email VARCHAR(45),
  telefone VARCHAR(15),
  revistinha VARCHAR(17),
  quantidade INT,
  atracoes VARCHAR(500),
  sugestao BOOLEAN,
  imagem BLOB
);
