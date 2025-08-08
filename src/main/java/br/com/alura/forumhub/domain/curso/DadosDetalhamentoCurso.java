package br.com.alura.forumhub.domain.curso;

public record DadosDetalhamentoCurso(Long id,
                                     String nome,
                                     CategoriaCursos categoria) {

    public DadosDetalhamentoCurso(Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}
