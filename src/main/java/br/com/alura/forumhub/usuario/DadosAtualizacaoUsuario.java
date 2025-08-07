package br.com.alura.forumhub.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizacaoUsuario(

        @NotBlank
        String nome,

        @NotBlank
        @Email
        String email,

        @NotNull
        String senha) {
}
