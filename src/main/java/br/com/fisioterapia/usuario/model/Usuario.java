package br.com.fisioterapia.usuario.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_usuario")
public class Usuario {

   @Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq")
@SequenceGenerator(name = "usuario_seq", sequenceName = "SEQ_USUARIO", allocationSize = 1)
    private Long id;

    
    private String nome;

    private String senha;

    private String email;

    private String cpf;

    private String telefone;

    private String funcao; // ADMINISTRADOR, FISIOTERAPEUTA, ATENDENTE

    private Boolean ativo = true;

    private String crefitoNumero;

    private String crefitoUf;

    private Integer tempoSessaoMinutos;

    // Relacionamento com as permissões reversas por tela do sistema
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UsuarioPermissaoTela> permissoesTelas = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getCrefitoNumero() {
        return crefitoNumero;
    }

    public void setCrefitoNumero(String crefitoNumero) {
        this.crefitoNumero = crefitoNumero;
    }

    public String getCrefitoUf() {
        return crefitoUf;
    }

    public void setCrefitoUf(String crefitoUf) {
        this.crefitoUf = crefitoUf;
    }

    public Integer getTempoSessaoMinutos() {
        return tempoSessaoMinutos;
    }

    public void setTempoSessaoMinutos(Integer tempoSessaoMinutos) {
        this.tempoSessaoMinutos = tempoSessaoMinutos;
    }

    public List<UsuarioPermissaoTela> getPermissoesTelas() {
        return permissoesTelas;
    }

    public void setPermissoesTelas(List<UsuarioPermissaoTela> permissoesTelas) {
        this.permissoesTelas = permissoesTelas;
    }

}
