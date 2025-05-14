CREATE TABLE tb_estado(
  id bigint not null auto_increment,
  nome varchar(100),

  primary key (id)
) engine=InnoDB default charset=utf8

CREATE TABLE tb_restaurante(
  id bigint not null auto_increment,
  taxa_frete decimal(12,2) not null,
  cozinha_id bigint not null,

  primary key(id)
) engine=InnoDB default charset=utf8

CREATE TABLE tb_cidade(
  id bigint not null auto_increment,
  nome varchar(100),
  estado_id bigint not null,

  primary key(id)
)

CREATE TABLE tb_formapagamento(
  id bigint not null auto_increment,
  DescricaoForma varchar(100),

  primary key(id)
)