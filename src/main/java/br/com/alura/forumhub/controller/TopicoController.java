package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.topico.DadosCadastroTopicos;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroTopicos dados){
        System.out.println(dados);
    }

}
