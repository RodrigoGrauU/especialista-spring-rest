insert into cozinha(id, nome) values(1, 'Tailandesa');
insert into cozinha(id, nome) values(2, 'Portuguesa');

insert into estado(nome) values('Manaus');
insert into estado(nome) values('Pará');
insert into estado(nome) values('Amapá');
insert into estado(nome) values('Acre');

insert into cidade(nome, estado_id) values('Belém', 2);
insert into cidade(nome, estado_id) values('Castanhal', 2);
insert into cidade(nome, estado_id) values('Bragança', 2);

insert into restaurante(nome, taxa_frete, id_cozinha, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id) values('Sabor Azulino', 5.0, 1, 'Jurunas', '35322-315', 'perto da casa 2', 'logradouro 1', '112', 1);
insert into restaurante(nome, taxa_frete, id_cozinha, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id) values('Comida Caseira', 2.0, 1, 'Cordel', '35322-999', 'perto da casa 22', 'logradouro 1324', '11212', 2);
insert into restaurante(nome, taxa_frete, id_cozinha, endereco_bairro, endereco_cep, endereco_complemento, endereco_logradouro, endereco_numero, endereco_cidade_id) values('Comida para Viagem', 1.0, 2, 'Rio Mar', '353111-315', 'perto da casa 123213', 'logradouro 213', '112112', 3);

insert into forma_pagamento(descricao) values('cartao de crédito');
insert into forma_pagamento(descricao) values('cartao de débito');
insert into forma_pagamento(descricao) values('dinheiro');

insert into permissao(nome, descricao) values('admin', 'pode tudo');
insert into permissao(nome, descricao) values('consulta', 'pode apenas consultar');


insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 2);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1, 3);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (2, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (3, 1);
insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (3, 2);