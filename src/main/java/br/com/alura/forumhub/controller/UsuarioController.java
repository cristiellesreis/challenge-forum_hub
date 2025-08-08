package br.com.alura.forumhub.controller;

import br.com.alura.forumhub.domain.usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<String> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados){

        repository.save(new Usuario(dados));
        return ResponseEntity.ok("Usuario cadastrado com sucesso!");
    }

    @GetMapping
    public Page<DadosListagemUsuario> listar(@PageableDefault(size=10, sort = {"nome"}) Pageable paginacao){
        return repository.findAll(paginacao).map(DadosListagemUsuario::new);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosListagemUsuario> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoUsuario dados) {
        var usuario = repository.getReferenceById(id);

        usuario.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosListagemUsuario(usuario));
    }
}
