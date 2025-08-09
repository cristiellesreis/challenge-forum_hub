package br.com.alura.forumhub.domain.usuario;

import br.com.alura.forumhub.domain.perfil.Perfil;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuarios")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String senha;

    @ManyToMany
    @JoinTable(name = "usuarios_perfis", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "perfil_id"))
    Set<Perfil> perfis;


    public Usuario(DadosUsuario dados) {
        this.nome = dados.nome();
        this.perfis = dados.perfis().stream()
                .map(nome -> new Perfil(null, nome))
                .collect(Collectors.toSet());
    }

    public Usuario(DadosCadastroUsuario dados, Set<Perfil> perfis) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.perfis = perfis;
    }

    public void atualizarInformacoes(@Valid DadosAtualizacaoUsuario dados, Set<Perfil> perfis) {

        if (dados.nome() != null) {
            this.nome = dados.nome();
        }

        if (dados.email() != null) {
            this.email = dados.email();
        }

        if (dados.senha() != null) {
            this.senha = dados.senha();
        }

        if (perfis != null && !perfis.isEmpty()) {
            this.perfis.clear();
            this.perfis.addAll(perfis);
        }
    }

}
