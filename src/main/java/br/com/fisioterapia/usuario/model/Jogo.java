package br.com.fisioterapia.usuario.model;

import jakarta.persistence.*;

@Entity
@Table(name = "game_jogo")
public class Jogo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

@Column(name = "imagem_capa")
private String imagemCapa;

// Getter e Setter
public String getImagemCapa() { return imagemCapa; }
public void setImagemCapa(String imagemCapa) { this.imagemCapa = imagemCapa; }

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 1)
    private String jogado; // "S" ou "N"

    @Column(columnDefinition = "TEXT")
    private String observacao;

    private String categoria;

    @Column(name = "vontade_jogar")
    private Integer vontadeJogar; // Ex: nota/prioridade de 1 a 5 ou 1 a 10

    // Construtores
    public Jogo() {}

    public Jogo(String nome, String jogado, String observacao, String categoria, Integer vontadeJogar) {
        this.nome = nome;
        this.jogado = jogado;
        this.observacao = observacao;
        this.categoria = categoria;
        this.vontadeJogar = vontadeJogar;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getJogado() { return jogado; }
    public void setJogado(String jogado) { this.jogado = jogado; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public Integer getVontadeJogar() { return vontadeJogar; }
    public void setVontadeJogar(Integer vontadeJogar) { this.vontadeJogar = vontadeJogar; }
}