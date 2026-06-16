package br.com.fisioterapia.usuario.dto;

import br.com.fisioterapia.usuario.model.Prontuario;
import br.com.fisioterapia.usuario.model.Usuario;


public class AgendamentoDto {

    private Long id;
    private String dtHoraInicial;
    private String dtHoraFinal;
    private String tipoSessao;
    private String icSituacao;
    private Prontuario paciente;
    private String dsObservacaoQueixas;
    private Usuario usuario;

    public String getDtHoraInicial() {
        return dtHoraInicial;
    }
    public void setDtHoraInicial(String dtHoraInicial) {
        this.dtHoraInicial = dtHoraInicial;
    }
    public String getDtHoraFinal() {
        return dtHoraFinal;
    }
    public void setDtHoraFinal(String dtHoraFinal) {
        this.dtHoraFinal = dtHoraFinal;
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

    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDsObservacaoQueixas() {
        return dsObservacaoQueixas;
    }
    public void setDsObservacaoQueixas(String dsObservacaoQueixas) {
        this.dsObservacaoQueixas = dsObservacaoQueixas;
    }
    public Prontuario getPaciente() {
        return paciente;
    }
    public void setPaciente(Prontuario paciente) {
        this.paciente = paciente;
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    

    


    

}
