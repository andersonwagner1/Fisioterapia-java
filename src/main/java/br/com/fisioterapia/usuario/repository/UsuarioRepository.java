package br.com.fisioterapia.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fisioterapia.usuario.model.Usuario;



@Repository
public abstract interface UsuarioRepository extends JpaRepository<Usuario, Long>{

    @Query("SELECT x from Usuario x WHERE x.email = :email and x.senha = :senha")
    Usuario consultarUsuarioSenha(@Param("email") String email, @Param("senha") String senha);

     @Query("SELECT x from Usuario x where x.email = 'ADM'")
    Usuario consultarUsuarioAdministrador();
}
