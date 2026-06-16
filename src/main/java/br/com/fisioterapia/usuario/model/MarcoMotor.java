package br.com.fisioterapia.usuario.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_marco_motor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MarcoMotor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "valor")
    private Integer valor;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @Column(name = "tempo_min")
    private String tempoMin;

    @Column(name = "tempo_max")
    private String tempoMax;
}