package br.com.alura.forumhub.domain.topico;

import br.com.alura.forumhub.domain.curso.DadosListagemCurso;
import br.com.alura.forumhub.domain.usuario.DadosUsuario;

import java.time.LocalDateTime;

public record DadosListagemTopico(Long id,
                                  String titulo,
                                  String mensagem,
                                  LocalDateTime data,
                                  StatusTopico status,
                                  DadosUsuario autor,
                                  DadosListagemCurso curso,
                                  String respostas) {


    public DadosListagemTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(), topico.getData(), topico.getStatus(), new DadosUsuario(topico.getAutor().getId(), topico.getAutor().getNome()), new DadosListagemCurso(topico.getCurso()), topico.getRespostas());
    }
}
