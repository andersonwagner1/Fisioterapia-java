package br.com.fisioterapia.usuario.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.fisioterapia.usuario.model.Jogo;

import java.util.List;

@Repository
public interface JogoRepository extends JpaRepository<Jogo, Long> {
    // Exemplo de busca personalizada por jogos já jogados ou por categoria
    List<Jogo> findByJogado(String jogado);
    List<Jogo> findByCategoriaIgnoreCase(String categoria);
    @Query("SELECT j FROM Jogo j WHERE j.vontadeJogar > 0 ORDER BY j.nome asc")
    List<Jogo> buscarRegistroVontadoJogarMaiorQueZero1();

       @Query("SELECT j FROM Jogo j WHERE j.vontadeJogar > 1")
 
       List<Jogo> buscarRegistroVontadoJogarMaiorQueZero(Pageable pageable);
 
    @Query("SELECT j FROM Jogo j WHERE j.nome = :nome")
       
       Jogo buscarJogoPorNome(String nome);

}