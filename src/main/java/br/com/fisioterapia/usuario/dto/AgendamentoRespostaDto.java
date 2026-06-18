package br.com.fisioterapia.usuario.dto;

import java.util.Date;


public class AgendamentoRespostaDto {

    private Long id;
    private String horaInicial;
    private String hroaFinal;
    private String tipoSessao;
    private String icSituacao;
    private String nome;
    private Long prontuarioId;
    private String profissional;
    private Date dtInicial;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getHoraInicial() {
        return horaInicial;
    }
    public void setHoraInicial(String horaInicial) {
        this.horaInicial = horaInicial;
    }
    public String getHroaFinal() {
        return hroaFinal;
    }
    public void setHroaFinal(String hroaFinal) {
        this.hroaFinal = hroaFinal;
    }
    public String getTipoSessao() {
        return tipoSessao;
    }
    public void setTipoSessao(String tipoSessao) {
        this.tipoSessao = tipoSessao;
    }
    public String getIcSituacao() {
        return icSituacao;
    }
    public void setIcSituacao(String icSituacao) {
        this.icSituacao = icSituacao;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getProfissional() {
        return profissional;
    }
    public void setProfissional(String profissional) {
        this.profissional = profissional;
    }
    public Date getDtInicial() {
        return dtInicial;
    }
    public void setDtInicial(Date dtInicial) {
        this.dtInicial = dtInicial;
    }
    public Long getProntuarioId() {
        return prontuarioId;
    }
    public void setProntuarioId(Long prontuarioId) {
        this.prontuarioId = prontuarioId;
    }
    

    

    


    

}
