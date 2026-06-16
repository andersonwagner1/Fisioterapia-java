package br.com.fisioterapia.usuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fisioterapia.usuario.model.Usuario;



@Repository
public abstract interface UsuarioRepository extends JpaRepository<Usuario, Long>{
}
