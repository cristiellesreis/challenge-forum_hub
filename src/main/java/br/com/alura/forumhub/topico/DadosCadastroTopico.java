package br.com.alura.forumhub.topico;

import br.com.alura.forumhub.curso.DadosCadastroCurso;
import br.com.alura.forumhub.usuario.DadosUsuario;

import java.time.LocalDateTime;

public record DadosCadastroTopico(Long id,
                                  String titulo,
                                  String mensagem,
                                  LocalDateTime data,
                                  StatusTopico status,
                                  DadosUsuario autor,
                                  DadosCadastroCurso curso) {
}
