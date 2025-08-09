package br.com.alura.forumhub.domain.resposta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoResposta(@NotBlank
                                       String mensagem,

                                       @NotNull
                                       Long topicoId,

                                       @NotNull
                                       Long autorId) {
}
