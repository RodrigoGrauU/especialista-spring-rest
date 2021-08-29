insert into cozinha(id, nome) values(1, 'Tailandesa');
insert into cozinha(id, nome) values(2, 'Portuguesa');
insert into cozinha (id, nome) values (3, 'Argentina');
insert into cozinha (id, nome) values (4, 'Brasileira');

insert into estado(nome) values('Manaus');
insert into estado(nome) values('Pará');
insert into estado(nome) values('Amapá');
insert into estado(nome) values('Acre');

insert into cidade(nome, estado_id) values('Belém', 2);
insert into cidade(nome, estado_id) values('Castanhal', 2);
insert into cidade(nome, estado_id) values('Bragança', 2);

insert into restaurante (nome, taxa_frete, id_cozinha, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id, data_cadastro, data_atualizacao) values('Sabor Azulino', 5.0, 1, 'Jurunas', '35322-315', 'perto da casa 2', 'logradouro 1', '112', 1, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, id_cozinha, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id, data_cadastro, data_atualizacao) values('Comida Caseira', 2.0, 1, 'Cordel', '35322-999', 'perto da casa 22', 'logradouro 1324', '11212', 2, utc_timestamp, utc_timestamp);
insert into restaurante (nome, taxa_frete, id_cozinha, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id, data_cadastro, data_atualizacao) values('Comida para Viagem', 1.0, 2, 'Rio Mar', '353111-315', 'perto da casa 123213', 'logradouro 213', '112112', 3, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, id_cozinha, data_cadastro, data_atualizacao) values (4, 'Java Steakhouse', 12, 3, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, id_cozinha, data_cadastro, data_atualizacao) values (5, 'Lanchonete do Tio Sam', 11, 4, utc_timestamp, utc_timestamp);
insert into restaurante (id, nome, taxa_frete, id_cozinha, data_cadastro, data_atualizacao) values (6, 'Bar da Maria', 6, 4, utc_timestamp, utc_timestamp);

insert into forma_pagamento(descricao) values('cartao de crédito');
insert into forma_pagamento(descricao) values('cartao de débito');
insert into forma_pagamento(descricao) values('dinheiro');


insert into permissao(nome, descricao) values('admin', 'pode tudo');
insert into permissao(nome, descricao) values('consulta', 'pode apenas consultar');

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into produto (nome, descricao, preco, ativo, restaurante_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);

--insert into produto (ativo, descricao, nome, preco, restaurante_id) values(true, 'descricao do produto', 'Arroz', 3.45, 1);
--insert into produto (ativo, descricao, nome, preco, restaurante_id) values(true, 'descricao do produto', 'Feijão', 6.45, 1);
--insert into produto (ativo, descricao, nome, preco, restaurante_id) values(true, 'descricao do produto', 'Macarrão', 6.45, 1);