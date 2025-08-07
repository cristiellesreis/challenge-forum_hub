package br.com.alura.forumhub.usuario;

public record DadosListagemUsuario(String nome) {

    public DadosListagemUsuario(Usuario usuario){
        this(usuario.getNome());
    }
}
