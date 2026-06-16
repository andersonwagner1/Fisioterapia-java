package br.com.fisioterapia.usuario.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_usuario_permissao_tela")
public class UsuarioPermissaoTela {

   @Id
@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_permissao_tela_seq")
@SequenceGenerator(name = "usuario_permissao_tela_seq", sequenceName = "SEQ_USUARIO_PERMISSAO_TELA", allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "tela_id", nullable = false, length = 50)
    private String telaId; // 'painel', 'agenda', 'prontuario', 'config'

    @Column(nullable = false)
    private Boolean visualizar;

    @Column(nullable = false)
    private Boolean incluir;

    @Column(nullable = false)
    private Boolean alterar;

    // Getters e Setters convencionais...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public String getTelaId() { return telaId; }
    public void setTelaId(String telaId) { this.telaId = telaId; }
    public Boolean getVisualizar() { return visualizar; }
    public void setVisualizar(Boolean visualizar) { this.visualizar = visualizar; }
    public Boolean getIncluir() { return incluir; }
    public void setIncluir(Boolean incluir) { this.incluir = incluir; }
    public Boolean getAlterar() { return alterar; }
    public void setAlterar(Boolean alterar) { this.alterar = alterar; }
}