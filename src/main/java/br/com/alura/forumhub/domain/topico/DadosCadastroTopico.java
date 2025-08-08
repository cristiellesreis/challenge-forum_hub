package br.com.alura.forumhub.domain.topico;

import br.com.alura.forumhub.domain.curso.DadosCadastroCurso;
import br.com.alura.forumhub.domain.usuario.DadosUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(

        Long id,

        @NotBlank
        String titulo,

        @NotBlank
        String mensagem,

        @NotNull
        StatusTopico status,

        @NotNull
        DadosUsuario autor,

        @NotNull
        DadosCadastroCurso curso) {
}
