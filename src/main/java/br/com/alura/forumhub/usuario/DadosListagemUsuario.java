package br.com.alura.forumhub.usuario;

public record DadosListagemUsuario(Long id, String nome) {

    public DadosListagemUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome());
    }
}
