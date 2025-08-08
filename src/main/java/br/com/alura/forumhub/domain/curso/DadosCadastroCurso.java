package br.com.alura.forumhub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroCurso(

        Long id,

        @NotBlank
        String nome,

        @NotNull
        CategoriaCursos categoria) {
}
