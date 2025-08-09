package br.com.alura.forumhub.domain.usuario;

import br.com.alura.forumhub.domain.perfil.Perfil;

import java.util.Set;
import java.util.stream.Collectors;

public record DadosDetalhamentoUsuario(Long id,
                                       String nome,
                                       String email,
                                       Set<String> perfis) {

    public DadosDetalhamentoUsuario(Usuario usuario) {

        this(usuario.getId(), usuario.getNome(), usuario.getEmail(),
                usuario.getPerfis().stream()
                        .map(Perfil::getNome)
                        .collect(Collectors.toSet()));
    }
}
