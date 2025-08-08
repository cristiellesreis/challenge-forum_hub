package br.com.alura.forumhub.domain.topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(String titulo,
                                      String mensagem,
                                      LocalDateTime data,
                                      StatusTopico status,
                                      String autor,
                                      String curso) {

    public DadosDetalhamentoTopico(Topico topico) {

        this(
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getData(),
                topico.getStatus(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome()
        );
    }
}

