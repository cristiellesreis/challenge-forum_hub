CREATE TABLE respostas (
    id BIGINT not null AUTO_INCREMENT,
    mensagem TEXT NOT NULL,
    data DATETIME NOT NULL,
    solucao BOOLEAN NOT NULL,
    topico_id BIGINT NOT NULL,
    autor_id BIGINT NOT NULL,

    PRIMARY KEY (id),

    CONSTRAINT fk_topico FOREIGN KEY (topico_id) REFERENCES topicos(id),
    CONSTRAINT fk_autor FOREIGN KEY (autor_id) REFERENCES usuarios(id)

);