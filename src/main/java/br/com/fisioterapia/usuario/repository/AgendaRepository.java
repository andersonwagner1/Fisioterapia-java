package br.com.fisioterapia.usuario.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.fisioterapia.usuario.model.Agendamento;

@Repository
public abstract interface AgendaRepository extends JpaRepository<Agendamento, Long>{

    @Query("SELECT x from Agendamento x WHERE CAST(x.dataHoraInicio AS localdate) = CAST(:dtFiltro AS localdate) ORDER BY x.dataHoraInicio")
    List<Agendamento> listarDataPorData(@Param("dtFiltro") LocalDateTime localDateTime);
}
