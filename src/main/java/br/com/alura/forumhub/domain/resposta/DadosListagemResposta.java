package br.com.alura.forumhub.domain.resposta;

import java.time.LocalDateTime;

public record DadosListagemResposta(Long id,
                                    String mensagem,
                                    LocalDateTime data,
                                    String autorNome,
                                    Boolean solucao) {

    public DadosListagemResposta(Resposta resposta) {
        this(resposta.getId(), resposta.getMensagem(), resposta.getData(), resposta.getAutor().getNome(), resposta.getSolucao());
    }
}
