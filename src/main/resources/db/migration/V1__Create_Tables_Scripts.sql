-- Criação da tabela role

CREATE TABLE role (
    role VARCHAR(100) PRIMARY KEY,
    descricao VARCHAR(100),
    ativo BOOLEAN,
    data_criacao TIMESTAMP DEFAULT now(),
    data_atualizacao TIMESTAMP DEFAULT now()
);

INSERT INTO role (role, ativo)
    VALUES ('ADMIN', true);

INSERT INTO role (role, ativo)
    VALUES ('USUARIO', true);

-- Criação da tabela usuario
CREATE TABLE usuario (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    cpf VARCHAR(11) UNIQUE,
    login VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    data_criacao TIMESTAMP DEFAULT now(),
    data_atualizacao TIMESTAMP DEFAULT now(),
    role_id VARCHAR(100) REFERENCES role(role) ON DELETE SET NULL
);

INSERT INTO usuario (id, nome, cpf, login, senha, role_id)
    VALUES ('e389406d-5531-4acf-a354-be5cc46a8cd4', 'Usuário padrão do sistema', '00000000000', 'admin', '$2a$12$waEH2aHEELRZu2z1iGDxqeKb1eBRB6kzzZSfeQ60RLf4tovEBQgXu', 'USUARIO');
