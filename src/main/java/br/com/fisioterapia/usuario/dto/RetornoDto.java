package br.com.fisioterapia.usuario.dto;

public class RetornoDto<T> {

    private boolean situacao;
    private String descricao;
    private T resultado;
    public boolean isSituacao() {
        return situacao;
    }
    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public T getResultado() {
        return resultado;
    }
    public void setResultado(T resultado) {
        this.resultado = resultado;
    }

    
    // Getters and Setters
}
