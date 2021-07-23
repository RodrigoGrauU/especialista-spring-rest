insert into cozinha(id, nome) values(1, 'Tailandesa');
insert into cozinha(id, nome) values(2, 'Portuguesa');

insert into restaurante(nome, taxa_frete, id_cozinha) values('Sabor Azulino', 5.0, 1);
insert into restaurante(nome, taxa_frete, id_cozinha) values('Comida Caseira', 2.0, 1);
insert into restaurante(nome, taxa_frete, id_cozinha) values('Comida para Viagem', 1.0, 2);

insert into forma_pagamento(descricao) values('cartao de crédito');
insert into forma_pagamento(descricao) values('cartao de débito');
