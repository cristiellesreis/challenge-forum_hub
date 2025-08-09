package br.com.alura.forumhub.domain.curso;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoCurso(

        Long id,

        String nome,

        CategoriaCursos categoria) {
}
