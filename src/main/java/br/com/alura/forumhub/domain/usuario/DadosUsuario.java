package br.com.alura.forumhub.domain.usuario;

import br.com.alura.forumhub.domain.perfil.Perfil;

import java.util.Set;
import java.util.stream.Collectors;

public record DadosUsuario(
        Long id,
        String nome,
        Set<String> perfis) {

    public DadosUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getNome(), usuario.getPerfis().stream()
                .map(Perfil::getNome)
                .collect(Collectors.toSet()));
    }
}
