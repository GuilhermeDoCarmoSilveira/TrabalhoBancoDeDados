CREATE DATABASE TrocaFacil

GO

USE TrocaFacil

GO

CREATE TABLE Usuario (
id_usuario		int				not null	IDENTITY (1,1),
email	varchar(100)	not null	UNIQUE,
senha	varchar(30)		not null
primary key(id_usuario)
)

GO

CREATE TABLE Empresa (
cnpj			char(14)		not null	UNIQUE Check (len(cnpj)=14),
razaoSocial		varchar(150)	not null,
apelido			varchar(50)		not null,
telefone		char(11)		not null Check (len(telefone)=11),
id_usuario		int				not null
primary key(cnpj)
foreign key (id_usuario) references Usuario (id_usuario)
)

GO

CREATE TABLE Cliente (
cpf				char(11)		not null	UNIQUE Check (len(cpf)=11),
nome			varchar(150)	not null,
telefone		char(11)		not null Check (len(telefone)=11),
dataNascimento	Date			not null Check (dataNascimento < getDate()), Check(DateDiff(Year,dataNascimento,getDate())>=18),
sexo			char(1)			not null Check(Upper(sexo) = 'M' or Upper(sexo) =	'F'),
id_usuario		int				not null
primary key(cpf)
foreign key (id_usuario) references Usuario (id_usuario)
)

GO

CREATE TABLE Endereco (
cep			char(8)			not null Check (len(cep)=8),
numero		int				not null,
id_usuario	int				not null,
titulo		varchar(30)		not null,
logradouro	varchar(200)	not null,
complemento	varchar(70)		null,
informacao	varchar(100)	null
primary key(cep, numero, id_usuario)
foreign key (id_usuario) references Usuario (id_usuario)
)

GO

CREATE TABLE Produto (
id_produto		int				not null IDENTITY (1,1), 
nome			varchar(100)	not null,
quantidade		int				not null,
preco			decimal(7,2)	not null,
descricao		varchar(255)	not null,
categoria		varchar(200)	not null,
statusProduto	char(1)			not null,
dataCadastro	date			not null,
id_usuario		int				not null
primary key	(id_produto)
foreign key (id_usuario) references Usuario (id_usuario)
)

GO

CREATE TABLE Pedido (
numero				int				not null,
dataRealizacao		date			not null,
quantidade			int				not null,
valorTotal			decimal(7,2)	not null,
id_produto			int				not null,
id_usuario			int				not null
primary key (numero, id_usuario, id_produto)
foreign key (id_usuario) references Usuario (id_usuario),
foreign key (id_produto) references Produto (id_produto)
)
