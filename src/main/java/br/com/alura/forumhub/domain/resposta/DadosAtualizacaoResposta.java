package br.com.alura.forumhub.domain.resposta;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoResposta(String mensagem,
                                       Boolean solucao) {
}
