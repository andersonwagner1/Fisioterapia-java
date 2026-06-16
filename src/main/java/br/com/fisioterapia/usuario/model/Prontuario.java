package br.com.fisioterapia.usuario.model;


import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_prontuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prontuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    private String sexo;

    @Column(name = "idade_cronologica")
    private String idadeCronologica;

    private String igc;

    @Column(name = "peso_atual")
    private Double pesoAtual;

    private Double comprimento;
    
    private String pc; 
    private String pediatra;
    private String endereco;
    private String cep;
    
    @Column(name = "num_complemento")
    private String numComplemento;

    // Dados Familiares
    private String mae;
    
    @Column(name = "idata_de_mae")
    private Integer idatademae; // Mantido para compatibilidade com o legado informado
    
    @Column(name = "idade_mae")
    private Integer idadeMae;
    
    @Column(name = "classnameicacao")
    private String classNameicacao; // Mantido legado informado
    
    @Column(name = "profissao_mae")
    private String profissaoMae;
    
    @Column(name = "contato_mae")
    private String contatoMae;
    
    private String pai;
    
    @Column(name = "idade_pai")
    private Integer idadePai;
    
    @Column(name = "profissao_pai")
    private String profissaoPai;
    
    @Column(name = "contato_pai")
    private String contatoPai;
    
    private String irmaos;

    // Dados Clínicos de Avaliação Geral
    @Column(name = "data_avaliacao")
    private LocalDate dataAvaliacao;
    
    private String avaliador;
    private String local;
    private String classificacao;
    
    @Column(columnDefinition = "TEXT")
    private String intercorrencias;

    // Histórico de Gestação
    @Column(name = "numero_gestacao")
    private String numeroGestacao;
    
    @Column(name = "gestacao_multiplas")
    private String gestacaoMultiplas;
    
    @Column(name = "liquido_amniotico")
    private String liquidoAmniotico;
    
    @Column(name = "apresentacao_gestacao")
    private String apresentacaoGestacao;
    
    @Column(name = "intercorrencias_gestacao", columnDefinition = "TEXT")
    private String intercorrenciasGestacao;

    // Histórico de Parto e Pós-parto
    @Column(name = "tipo_parto")
    private String tipoParto;
    
    private String ign;
    
    @Column(name = "duracao_tp")
    private String duracaoTp;
    
    @Column(name = "intercorrencias_parto", columnDefinition = "TEXT")
    private String intercorrenciasParto;
    
    @Column(name = "peso_nascimento")
    private Double pesoNascimento;
    
    private String apgar;
    
    @Column(name = "comprimento_nascimento")
    private Double comprimentoNascimento;
    
    @Column(name = "pc_nascimento")
    private Double pcNascimento;
    
    @Column(name = "internacao_pos_parto")
    private String internacaoPosParto;
    
    @Column(name = "intercorrencia_pos_parto", columnDefinition = "TEXT")
    private String intercorrenciaPosParto;

    // Rotinas e Hábitos Diários
    @Column(name = "dieta_patologias", columnDefinition = "TEXT")
    private String dietaPatologias;
    
    @Column(name = "atividades_diarias", columnDefinition = "TEXT")
    private String atividadesDiarias;
    
    @Column(name = "habilidades_motoras", columnDefinition = "TEXT")
    private String habilidadesMotoras;
    
    @Column(name = "periodo_sono")
    private String periodoSono;
    
    @Column(name = "posicao_preferencia")
    private String posicaoPreferencia;
    
    @Column(name = "acompanhamento_fisio_osteo")
    private String acompanhamentoFisioOsteo;
    
    @Column(name = "desenvolvimento_cognitivo", columnDefinition = "TEXT")
    private String desenvolvimentoCognitivo;

    // Motivo da Consulta
    @Column(name = "queixa_principal", columnDefinition = "TEXT")
    private String queixaPrincipal;

    // Exame Postural
    @Column(name = "alinhamento_estatico", columnDefinition = "TEXT")
    private String alinhamentoEstatico;
    
    @Column(name = "controle_cervical_tronco", columnDefinition = "TEXT")
    private String controleCervicalTronco;
    
    @Column(name = "tonus_muscular", columnDefinition = "TEXT")
    private String tonusMuscular;

    // Resultados Escala Alberta (AIMS)
    @Column(name = "score_prono")
    private Integer scoreProno;
    
    @Column(name = "score_supino")
    private Integer scoreSupino;
    
    @Column(name = "score_sentado")
    private Integer scoreSentado;
    
    @Column(name = "score_em_pe")
    private Integer scoreEmPe;
    
    @Column(name = "score_total_alberta")
    private Integer scoreTotalAlberta;
    
    @Column(name = "percentil_alberta")
    private Integer percentilAlberta;
    
    @Column(name = "classificacao_alberta")
    private String classificacaoAlberta;
    
    @Column(name = "obs_alberta", columnDefinition = "TEXT")
    private String obsAlberta;
/*
    // Tabelas Auxiliares para Coleções Elementares (number[])
    @ElementCollection
    @CollectionTable(name = "prontuario_marcos_prono", joinColumns = @JoinColumn(name = "prontuario_id"))
    @Column(name = "marco_valor")
    private List<Integer> marcosPronoSelecionados = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "prontuario_marcos_supino", joinColumns = @JoinColumn(name = "prontuario_id"))
    @Column(name = "marco_valor")
    private List<Integer> marcosSupinoSelecionados = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "prontuario_marcos_sentado", joinColumns = @JoinColumn(name = "prontuario_id"))
    @Column(name = "marco_valor")
    private List<Integer> marcosSentadoSelecionados = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "prontuario_marcos_em_pe", joinColumns = @JoinColumn(name = "prontuario_id"))
    @Column(name = "marco_valor")
    private List<Integer> marcosEmPeSelecionados = new ArrayList<>();

    // Sublista Relacional (1:N)
    @OneToMany(mappedBy = "prontuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EvolucaoClinica> historicoEvolucoes = new ArrayList<>();
*/


}