-- Criação da tabela usuario
CREATE TABLE usuario (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    cpf VARCHAR(11) UNIQUE,
    data_criacao TIMESTAMP DEFAULT now(),
    data_atualizacao TIMESTAMP DEFAULT now()
);

INSERT INTO usuario (id, nome, email, cpf)
    VALUES ('e389406d-5531-4acf-a354-be5cc46a8cd4', '00000000000');


-- -- --
