package br.com.fisioterapia.usuario.model;


import jakarta.persistence.*;
import lombok.*;
import java.util.Date;


@Entity
@Table(name = "tb_agendamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agendamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  // Relacionamento ManyToOne substitui o prontuarioId e o nomePaciente soltos
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "prontuario_id", nullable = true) // nullable = true permite agendamentos sem prontuário (ex: primeira consulta)
    private Prontuario paciente;

// ... dentro da classe

@Column(name = "data_hora_inicio", nullable = false)
private Date dataHoraInicio;

@Column(name = "data_hora_fim", nullable = false)
private Date dataHoraFim;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(name = "tipo_sessao")
    private String tipoSessao;

    @Column(name = "status")
    private String status;

    @Column(name = "observacoes", columnDefinition = "TEXT")
    private String observacoes;
}
