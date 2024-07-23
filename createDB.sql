create schema brasil38k;

use brasil38k;

create table lista_para_envio (
	id INT NOT NULL AUTO_INCREMENT,
    lista VARCHAR(50),
	escola VARCHAR(200),
    inep INT,
    uf VARCHAR(5),
    municipio VARCHAR(200),
    endereco VARCHAR(400),
    telefone VARCHAR(30),
    porte VARCHAR(100),
    emeo VARCHAR(200),
    
	PRIMARY KEY(id)
);

alter table lista_para_envio add column cnpj INT;
alter table lista_para_envio add column email VARCHAR(50);
alter table lista_para_envio add column telefone2 VARCHAR(30);
alter table lista_para_envio add column telefone3 VARCHAR(30);
alter table lista_para_envio add column telefone4 VARCHAR(30);
alter table lista_para_envio add column site VARCHAR(50);
alter table lista_para_envio add column instagram VARCHAR(50);
alter table lista_para_envio add column sistema_de_ensino VARCHAR(50);
alter table lista_para_envio add column agenda_digital VARCHAR(50);
alter table lista_para_envio add column anotacoes VARCHAR(500);

ALTER TABLE lista_para_envio MODIFY inep BIGINT;
ALTER TABLE lista_para_envio MODIFY cnpj BIGINT;

ALTER TABLE lista_para_envio CHANGE COLUMN escola nome VARCHAR(200);