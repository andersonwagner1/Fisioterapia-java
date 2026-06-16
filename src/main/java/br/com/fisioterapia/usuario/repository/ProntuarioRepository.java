package br.com.fisioterapia.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fisioterapia.usuario.model.Prontuario;


@Repository
public abstract interface ProntuarioRepository extends JpaRepository<Prontuario, Long>{

    @Query("SELECT p FROM Prontuario p WHERE upper(p.nome) LIKE upper(%:nome%) order by p.nome")
    List<Prontuario> consultarPorNomePaciente(String nome);
}
