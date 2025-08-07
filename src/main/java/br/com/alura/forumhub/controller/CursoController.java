package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.curso.Curso;
import br.com.alura.forumhub.curso.CursoRepository;
import br.com.alura.forumhub.curso.DadosCadastroCurso;
import br.com.alura.forumhub.curso.DadosListagemCurso;
import br.com.alura.forumhub.topico.Topico;
import br.com.alura.forumhub.usuario.DadosListagemUsuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroCurso dados){
        repository.save(new Curso(dados));
    }

    @GetMapping
    public Page<DadosListagemCurso> listar(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemCurso::new);
    }
}
