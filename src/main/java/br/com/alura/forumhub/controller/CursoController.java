package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.domain.curso.*;
import br.com.alura.forumhub.domain.usuario.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("cursos")
public class CursoController {

    @Autowired
    private CursoRepository repository;

    @Operation(summary = "Cadastra um curso", security = @SecurityRequirement(name = "bearer-key"))
    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCurso dados, UriComponentsBuilder uriBuilder) {
        var curso = new Curso(dados);
        repository.save(curso);

        var uri = uriBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCurso(curso));
    }

    @Operation(summary = "Lista os cursos", security = @SecurityRequirement(name = "bearer-key"))
    @GetMapping
    public ResponseEntity<Page<DadosListagemCurso>> listar(@PageableDefault(size = 10, sort = {"categoria"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemCurso::new);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Detalha um curso", security = @SecurityRequirement(name = "bearer-key"))
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var curso = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @Operation(summary = "Atualiza um curso", security = @SecurityRequirement(name = "bearer-key"))
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoCurso dados) {
        var curso = repository.getReferenceById(id);

        curso.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoCurso(curso));
    }

    @PreAuthorize("hasRole('ROLE_ADMINISTRADOR')")
    @Operation(summary = "Exclui um curso", security = @SecurityRequirement(name = "bearer-key"))
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
