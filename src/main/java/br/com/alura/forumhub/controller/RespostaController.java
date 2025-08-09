package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.domain.curso.DadosDetalhamentoCurso;
import br.com.alura.forumhub.domain.curso.DadosListagemCurso;
import br.com.alura.forumhub.domain.resposta.*;
import br.com.alura.forumhub.domain.topico.TopicoRepository;
import br.com.alura.forumhub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroResposta dados, UriComponentsBuilder uriBuilder) {
        var topico = topicoRepository.getReferenceById(dados.topicoId());
        var autor = usuarioRepository.getReferenceById(dados.autorId());

        var resposta = new Resposta(dados.mensagem(), topico, autor);
        repository.save(resposta);

        var uri = uriBuilder.path("/respostas/{id}").buildAndExpand(resposta.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoResposta(resposta));
    }

    @GetMapping("/topico/{id}")
    public ResponseEntity<Page<DadosListagemResposta>> listar(@PageableDefault(size = 10, sort = {"data"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemResposta::new);
        return ResponseEntity.ok(page);
    }


}
