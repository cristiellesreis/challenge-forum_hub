package br.com.alura.forumhub.domain.resposta;

import java.time.LocalDateTime;

public record DadosDetalhamentoResposta(Long id,
                                        String mensagem,
                                        LocalDateTime data,
                                        String autorNome,
                                        Boolean solucao,
                                        Long topicoId,
                                        String topicoTitulo) {

    public DadosDetalhamentoResposta(Resposta resposta) {
        this(resposta.getId(), resposta.getMensagem(), resposta.getData(), resposta.getAutor().getNome(), resposta.getSolucao(), resposta.getTopico().getId(), resposta.getTopico().getTitulo());
    }
}
