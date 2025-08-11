package br.com.alura.forumhub.infra;

import br.com.alura.forumhub.domain.perfil.Perfil;
import br.com.alura.forumhub.domain.perfil.PerfilRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class PerfilSeeder implements CommandLineRunner {
    private final PerfilRepository repository;

    public PerfilSeeder(PerfilRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {

        boolean criouAlgum = false;

        if (repository.findByNomeIgnoreCase("ADMINISTRADOR").isEmpty()) {
            repository.save(new Perfil(null, "ADMINISTRADOR"));
            criouAlgum = true;
        }

        if (repository.findByNomeIgnoreCase("USUARIO").isEmpty()) {
            repository.save(new Perfil(null, "USUARIO"));
            criouAlgum = true;
        }

        if (criouAlgum) {
            System.out.println("Perfis criados/atualizados no banco.");
        } else {
            System.out.println("Perfis já existem no banco.");
        }
    }
}
