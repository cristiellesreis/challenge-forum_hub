package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.curso.Curso;
import br.com.alura.forumhub.curso.CursoRepository;
import br.com.alura.forumhub.topico.DadosCadastroTopico;
import br.com.alura.forumhub.topico.Topico;
import br.com.alura.forumhub.topico.TopicoRepository;
import br.com.alura.forumhub.usuario.Usuario;
import br.com.alura.forumhub.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

        if (repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())){
            return ResponseEntity.badRequest().body("Tópico duplicado: título e mensagem já existem!");
        }

        Usuario autor = usuarioRepository.getReferenceById(dados.autor().id());
        Curso curso = cursoRepository.getReferenceById(dados.curso().id());

        Topico topico = new Topico(dados, autor, curso);
        repository.save(topico);

        return ResponseEntity.ok("Tópico cadastrado com sucesso");
    }

}
