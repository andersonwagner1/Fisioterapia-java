package br.com.fisioterapia.usuario.controller;


import jakarta.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.fisioterapia.usuario.dto.JogoDTO;
import br.com.fisioterapia.usuario.dto.JogoScrapedDTO;
import br.com.fisioterapia.usuario.model.Jogo;
import br.com.fisioterapia.usuario.repository.JogoRepository;
import br.com.fisioterapia.usuario.service.ImageStorageService;
import br.com.fisioterapia.usuario.service.ScraperService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/jogos")
@CrossOrigin(origins = "*") // Permite requisições do front-end
public class JogoController {

    private final ScraperService scraperService;
    private final ImageStorageService imageStorageService;

    private final JogoRepository repository;

    public JogoController(JogoRepository repository, ScraperService scraperService, ImageStorageService imageStorageService) {
        this.repository = repository;
         this.scraperService = scraperService;
         this.imageStorageService = imageStorageService;
    }

    @GetMapping
    public ResponseEntity<List<Jogo>>  listarTodos() {
        List<Jogo> resultados = repository.buscarRegistroVontadoJogarMaiorQueZero(PageRequest.of(0, 5000));
         return ResponseEntity.ok(resultados);
            
        
    
    }

    @GetMapping("/importar-site1")
    @Operation(summary = "Buscar jogos paginados do site SuperPSX")
    public ResponseEntity<List<JogoScrapedDTO>> buscarJogosExternos(
            @Parameter(description = "Número da página a ser consultada") 
            @RequestParam(defaultValue = "1") int pagina,
            @Parameter(description = "Quantidade de páginas a serem percorridas sequencialmente") 
            @RequestParam(defaultValue = "1") int totalPaginas) {
        try {
            List<JogoScrapedDTO> jogos = scraperService.buscarMultiplasPaginas(pagina, totalPaginas);
            return ResponseEntity.ok(jogos);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

   @PostMapping("/importar-site/salvar")
@Operation(summary = "Buscar do site, baixar imagens para disco e salvar no banco")
public ResponseEntity<List<Jogo>> importarESalvar(
        @RequestParam(defaultValue = "1") int pagina,
        @RequestParam(defaultValue = "1") int totalPaginas) {
    try {
        List<JogoScrapedDTO> extraidos = scraperService.buscarMultiplasPaginas(pagina, totalPaginas);
        List<Jogo> novosJogos = new ArrayList<>();

        for (JogoScrapedDTO dto : extraidos) {

            Jogo existeJogo = repository.buscarJogoPorNome(dto.nome());

            if(existeJogo != null){
                System.out.println("Jogo ja existe");
                continue;
            }else{System.out.println("novo jogo cadastrado " + dto.nome()) ;}
            
            Jogo jogo = new Jogo();
            jogo.setNome(dto.nome());
            jogo.setJogado("N");
            jogo.setCategoria("PS4");
            jogo.setObservacao("Link original: " + dto.linkPagina());
            jogo.setVontadeJogar(5);

            // Download da imagem e salvamento na pasta local
            try {
                String nomeImagemLocal = imageStorageService.baixarESalvarImagem(dto.imagemUrl());
                jogo.setImagemCapa(nomeImagemLocal);
                
            } catch (Exception e) {
                // Caso falhe o download de uma imagem específica, não interrompe o loop dos demais jogos
                jogo.setImagemCapa(null);
            }
            repository.save(jogo);
            novosJogos.add(jogo);
        }

        List<Jogo> salvos = repository.saveAll(novosJogos);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvos);
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
}
    @GetMapping("/importar-site")
    @Operation(
        summary = "Buscar jogos do site SuperPSX", 
        description = "Acessa a categoria de jogos de PS4 do site e extrai os nomes, capas e links das publicações."
    )
    @ApiResponse(responseCode = "200", description = "Lista de jogos extraída com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro ao tentar conectar ou ler o site de origem")
    public ResponseEntity<List<JogoScrapedDTO>> buscarJogosExternos() {
        try {
            List<JogoScrapedDTO> jogosEncontrados = scraperService.buscarJogosDoSite();
            return ResponseEntity.ok(jogosEncontrados);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Jogo> cadastrar(@Valid @RequestBody JogoDTO dto) {
        Jogo jogo = new Jogo();
        jogo.setNome(dto.nome());
        jogo.setJogado(dto.jogado().toUpperCase());
        jogo.setObservacao(dto.observacao());
        jogo.setCategoria(dto.categoria());
        jogo.setVontadeJogar(dto.vontadeJogar());

        Jogo salvo = repository.save(jogo);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jogo> atualizar(@PathVariable Long id, @Valid @RequestBody JogoDTO dto) {
        return repository.findById(id)
                .map(jogoExistente -> {
                    jogoExistente.setNome(dto.nome());
                    jogoExistente.setJogado(dto.jogado().toUpperCase());
                    jogoExistente.setObservacao(dto.observacao());
                    jogoExistente.setCategoria(dto.categoria());
                    jogoExistente.setVontadeJogar(dto.vontadeJogar());
                    return ResponseEntity.ok(repository.save(jogoExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
         Jogo resultado = repository.findById(id).get();
         resultado.setVontadeJogar(0); // Marca como "não quero jogar" antes de deletar
            resultado.setJogado("R");
            repository.save(resultado);
            return ResponseEntity.noContent().build();
        
        
    }
}