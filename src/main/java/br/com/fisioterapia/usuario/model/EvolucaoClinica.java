package br.com.fisioterapia.usuario.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tb_evolucao_clinica")
public class EvolucaoClinica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

  @ManyToOne(fetch = FetchType.EAGER)
      @JoinColumn(name = "prontuario_id")
    private Prontuario prontuario;

    @Column(name = "data_consulta")
    private LocalDate dataConsulta;

@ManyToOne(fetch = FetchType.EAGER) 
    @JoinColumn(name = "fisioterapeuta_id")
    private Usuario fisioterapeuta;

    @Column(name = "como_chegou")
    private String comoChegou;

    @Column(name = "procedimento")
    private String procedimento;

    @Column(name = "como_saiu")
    private String comoSaiu;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Prontuario getProntuario() {
        return prontuario;
    }

    public void setProntuario(Prontuario prontuario) {
        this.prontuario = prontuario;
    }

    public LocalDate getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(LocalDate dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Usuario getFisioterapeuta() {
        return fisioterapeuta;
    }

    public void setFisioterapeuta(Usuario fisioterapeuta) {
        this.fisioterapeuta = fisioterapeuta;
    }

    public String getComoChegou() {
        return comoChegou;
    }

    public void setComoChegou(String comoChegou) {
        this.comoChegou = comoChegou;
    }

    public String getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(String procedimento) {
        this.procedimento = procedimento;
    }

    public String getComoSaiu() {
        return comoSaiu;
    }

    public void setComoSaiu(String comoSaiu) {
        this.comoSaiu = comoSaiu;
    }
}
