package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.domain.resposta.*;
import br.com.alura.forumhub.domain.topico.TopicoRepository;
import br.com.alura.forumhub.domain.usuario.Usuario;
import br.com.alura.forumhub.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("respostas")
public class RespostaController {

    @Autowired
    private RespostaRepository repository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Operation(summary = "Cadastra uma resposta", security = @SecurityRequirement(name = "bearer-key"))
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroResposta dados, UriComponentsBuilder uriBuilder,
                                    @AuthenticationPrincipal Usuario usuario) {
        var topico = topicoRepository.getReferenceById(dados.topicoId());
        var autor = usuarioRepository.getReferenceById(usuario.getId());

        var resposta = new Resposta(dados.mensagem(), topico, autor);
        repository.save(resposta);

        var uri = uriBuilder.path("/respostas/{id}").buildAndExpand(resposta.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoResposta(resposta));
    }

    @Operation(summary = "Lista respostas paginadas do mais recente para o mais antigo", security = @SecurityRequirement(name = "bearer-key"))
    @GetMapping
    public ResponseEntity<Page<DadosListagemResposta>> listar(@PageableDefault(size = 10, sort = "data", direction = Sort.Direction.ASC) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemResposta::new);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "Detalha uma resposta", security = @SecurityRequirement(name = "bearer-key"))
    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var resposta = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoResposta(resposta));
    }

    @Operation(summary = "Atualiza uma resposta", security = @SecurityRequirement(name = "bearer-key"))
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id,
                                    @RequestBody @Valid DadosAtualizacaoResposta dados,
                                    @AuthenticationPrincipal Usuario usuario) {

        var resposta = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resposta não encontrada"));

        boolean isAdmin = usuario.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equalsIgnoreCase("ROLE_ADMINISTRADOR"));

        if (!isAdmin && !resposta.getAutor().getId().equals(usuario.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Você não tem permissão para atualizar esta resposta.");
        }

        resposta.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoResposta(resposta));
    }

    @Operation(summary = "Exclui uma resposta", security = @SecurityRequirement(name = "bearer-key"))
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id, @AuthenticationPrincipal Usuario usuario) {

        var resposta = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Resposta não encontrada"));

        boolean isAdmin = usuario.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equalsIgnoreCase("ROLE_ADMINISTRADOR"));

        if (!isAdmin && !resposta.getAutor().getId().equals(usuario.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Você não tem permissão para excluir esta resposta.");
        }

        repository.delete(resposta);
        return ResponseEntity.noContent().build();
    }
}
