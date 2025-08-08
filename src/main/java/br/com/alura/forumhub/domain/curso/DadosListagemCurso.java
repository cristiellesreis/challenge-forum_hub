package br.com.alura.forumhub.domain.curso;

public record DadosListagemCurso(Long id,
                                String nome,
                                CategoriaCursos categoria) {

    public DadosListagemCurso(Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
