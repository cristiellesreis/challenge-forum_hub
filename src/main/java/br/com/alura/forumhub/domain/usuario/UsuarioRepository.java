package br.com.alura.forumhub.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    @Query("SELECT u FROM Usuario u JOIN FETCH u.perfis WHERE u.email = :email")
    Optional<Usuario> findByEmailWithPerfis(@Param("email") String email);

}
