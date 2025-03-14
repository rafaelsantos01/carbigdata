
CREATE TABLE cliente (
                         cod_cliente SERIAL PRIMARY KEY,
                         nme_cliente VARCHAR(255) NOT NULL,
                         dta_nascimento DATE,
                         nro_cpf VARCHAR(14) UNIQUE NOT NULL,
                         dta_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE endereco (
                          cod_endereco SERIAL PRIMARY KEY,
                          nme_logradouro VARCHAR(255) NOT NULL,
                          nme_bairro VARCHAR(255) NOT NULL,
                          nro_cep VARCHAR(10) NOT NULL,
                          nme_cidade VARCHAR(100) NOT NULL,
                          nme_estado VARCHAR(50) NOT NULL
);

CREATE TABLE ocorrencia (
                            cod_ocorrencia SERIAL PRIMARY KEY,
                            cod_cliente INT NOT NULL,
                            cod_endereco INT NOT NULL,
                            dta_ocorrencia TIMESTAMP NOT NULL,
                            sta_ocorrencia VARCHAR(10) CHECK (sta_ocorrencia IN ('ATIVA', 'FINALIZADA')),
                            CONSTRAINT fk_ocorrencia_cliente FOREIGN KEY (cod_cliente) REFERENCES cliente (cod_cliente),
                            CONSTRAINT fk_ocorrencia_endereco FOREIGN KEY (cod_endereco) REFERENCES endereco (cod_endereco)
);

CREATE TABLE foto_ocorrencia (
                                 cod_foto_ocorrencia SERIAL PRIMARY KEY,
                                 cod_ocorrencia INT NOT NULL,
                                 dta_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                 dsc_path_bucket TEXT NOT NULL,
                                 dsc_hash TEXT NOT NULL,
                                 CONSTRAINT fk_foto_ocorrencia FOREIGN KEY (cod_ocorrencia) REFERENCES ocorrencia (cod_ocorrencia)
);

CREATE TABLE usuario (
                         cod_usuario SERIAL PRIMARY KEY,
                         username VARCHAR(255) UNIQUE NOT NULL,
                         password VARCHAR(255) NOT NULL
);
