package br.com.alura.forumhub.topico;

import br.com.alura.forumhub.curso.Curso;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoTopico(

        @NotNull
        Long id,

        String titulo,

        String mensagem,

        StatusTopico status) {
}
