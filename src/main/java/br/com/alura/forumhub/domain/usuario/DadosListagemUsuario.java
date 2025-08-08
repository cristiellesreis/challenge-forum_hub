package br.com.alura.forumhub.domain.usuario;

public record DadosListagemUsuario(Long id, String nome) {

    public DadosListagemUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome());
    }
}
