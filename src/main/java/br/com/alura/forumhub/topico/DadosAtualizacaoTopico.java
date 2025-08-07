package br.com.alura.forumhub.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(

        @NotBlank
        String titulo,

        @NotBlank
        String mensagem,

        @NotNull
        StatusTopico status) {
}
