package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.topico.DadosCadastroTopicos;
import br.com.alura.forumhub.topico.Topico;
import br.com.alura.forumhub.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroTopicos dados){
       repository.save(new Topico(dados));
    }

}
