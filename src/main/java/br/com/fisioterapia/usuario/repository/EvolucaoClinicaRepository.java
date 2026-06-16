package br.com.fisioterapia.usuario.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.fisioterapia.usuario.model.EvolucaoClinica;

@Repository
public abstract interface EvolucaoClinicaRepository extends JpaRepository<EvolucaoClinica, Long>{

    @Query("SELECT x FROM EvolucaoClinica x where x.prontuario.id = :idPaciente")
    List<EvolucaoClinica> listarEvolucaocaPorPaciente(Long idPaciente);
}
