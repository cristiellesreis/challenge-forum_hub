package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.curso.Curso;
import br.com.alura.forumhub.curso.CursoRepository;
import br.com.alura.forumhub.topico.*;
import br.com.alura.forumhub.usuario.Usuario;
import br.com.alura.forumhub.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid DadosCadastroTopico dados) {

        if (repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            return ResponseEntity.badRequest().body("Tópico duplicado: título e mensagem já existem!");
        }

        Usuario autor = usuarioRepository.getReferenceById(dados.autor().id());
        Curso curso = cursoRepository.getReferenceById(dados.curso().id());

        Topico topico = new Topico(dados, autor, curso);
        repository.save(topico);

        return ResponseEntity.ok("Tópico cadastrado com sucesso");
    }

    @GetMapping
    public Page<DadosListagemTopico> listar(@PageableDefault(size = 10, sort = "data", direction = Sort.Direction.ASC) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemTopico::new);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosListagemTopico> detalhar(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        var dadosListagemTopico = new DadosListagemTopico(topico);
        return ResponseEntity.ok(dadosListagemTopico);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados){
        var topico = repository.getReferenceById(dados.id());
        topico.atualizarInformacoes(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id){
        repository.deleteById(id);
    }
}
