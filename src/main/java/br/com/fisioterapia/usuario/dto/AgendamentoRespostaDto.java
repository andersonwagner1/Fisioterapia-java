package br.com.fisioterapia.usuario.dto;

import br.com.fisioterapia.usuario.model.Prontuario;
import br.com.fisioterapia.usuario.model.Usuario;


public class AgendamentoRespostaDto {

    private Long id;
    private String horaInicial;
    private String hroaFinal;
    private String tipoSessao;
    private String icSituacao;
    private String nome;
    private String profissional;
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
    

    

    


    

}
