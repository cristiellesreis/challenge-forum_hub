package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.domain.perfil.Perfil;
import br.com.alura.forumhub.domain.perfil.PerfilRepository;
import br.com.alura.forumhub.domain.usuario.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Operation(summary = "Cadastra um usuário")
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {

        if (repository.existsByEmail(dados.email())) {
            return ResponseEntity.badRequest().body("E-mail já cadastrado.");
        }

        Set<Perfil> perfis = new HashSet<>(perfilRepository.findAllById(dados.perfisIds()));
        if (perfis.isEmpty()) {
            return ResponseEntity.badRequest().body("Nenhum perfil encontrado para os IDs informados.");
        }

        var usuario = new Usuario(dados, perfis);
        repository.save(usuario);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @Operation(summary = "Lista usuários paginados em ordem alfabética", security = @SecurityRequirement(name = "bearer-key"))
    @GetMapping
    public ResponseEntity<Page<DadosListagemUsuario>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemUsuario::new);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Detalha um usuário", security = @SecurityRequirement(name = "bearer-key"))
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @Operation(summary = "Atualiza um usuário", security = @SecurityRequirement(name = "bearer-key"))
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoUsuario dados) {
        var usuario = repository.getReferenceById(id);

        Set<Perfil> perfis = new HashSet<>();
        if (dados.perfisIds() != null && !dados.perfisIds().isEmpty()) {
            perfis.addAll(perfilRepository.findAllById(dados.perfisIds()));
        }

        usuario.atualizarInformacoes(dados, perfis);

        return ResponseEntity.ok(new DadosListagemUsuario(usuario));
    }

    @Operation(summary = "Exclui um usuário", security = @SecurityRequirement(name = "bearer-key"))
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
