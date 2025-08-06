package br.com.alura.forumhub.topico;

import br.com.alura.forumhub.curso.DadosCurso;
import br.com.alura.forumhub.usuario.DadosUsuario;

import java.time.LocalDateTime;

public record DadosCadastroTopicos(Long id,
                                   String titulo,
                                   String mensagem,
                                   LocalDateTime data,
                                   StatusTopico status,
                                   DadosUsuario autor,
                                   DadosCurso curso) {
}
