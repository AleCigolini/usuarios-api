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
VALUES
    ('e389406d-5531-4acf-a354-be5cc46a8cd4', 'Usuario 01', '05297854296', 'usuario01', '$2a$12$BwH8ZJyKcnL0RjLJ1H.E3.eMz8dwNBImDZmbfrWFAty6e7k0TSex6', 'USUARIO'),
    ('a1234567-89ab-cdef-0123-456789abcdef', 'Usuario 02', '12345678901', 'usuario02', '$2a$12$6ga/QP0JBgQlyp78Z49XaenoYxnx5AZanumIbCqRty2ncflVmo7xS', 'USUARIO'),
    ('b2345678-90bc-def1-2345-67890bcdef12', 'Usuario 03', '23456789012', 'usuario03', '$2a$12$W3dAsoJ1b91Py2DEtlIgTumc4rgJ9rTfomwKlwGUDyJcoWLUbWF4C', 'USUARIO');
