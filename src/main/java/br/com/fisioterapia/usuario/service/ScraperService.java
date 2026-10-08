package br.com.fisioterapia.usuario.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import br.com.fisioterapia.usuario.dto.JogoScrapedDTO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScraperService {

    private static final String BASE_URL = "https://www.superpsx.com/category/ps4/ps4-games-free/";
private static final String URL_TARGET = "https://www.superpsx.com/category/ps4/ps4-games-free/";
/**
     * Busca jogos percorrendo um intervalo de páginas.
     */
    public List<JogoScrapedDTO> buscarMultiplasPaginas(int paginaInicial, int quantidadePaginas) throws IOException {
        List<JogoScrapedDTO> todosJogos = new ArrayList<>();
        for (int i = 0; i < quantidadePaginas; i++) {
            int paginaAtual = paginaInicial + i;
            todosJogos.addAll(buscarJogosPorPagina(paginaAtual));
        }
        return todosJogos;
    }

    /**
     * Busca jogos de uma página específica.
     * Exemplo: pagina = 1 -> URL base
     *          pagina = 2 -> BASE_URL + "page/2/"
     */
    public List<JogoScrapedDTO> buscarJogosPorPagina(int pagina) throws IOException {
        List<JogoScrapedDTO> jogos = new ArrayList<>();
        
        // Formata a URL de acordo com o número da página
        String urlTarget = (pagina <= 1) ? BASE_URL : BASE_URL + "page/" + pagina + "/";

        Document doc = Jsoup.connect(urlTarget)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                .referrer("https://www.google.com")
                .timeout(12000)
                .get();

        Elements elementos = doc.select("a.penci-image-holder, a[data-bgset]");

        for (Element elem : elementos) {
            String nomeJogo = elem.attr("title");
            if (nomeJogo.isBlank()) {
                nomeJogo = elem.attr("aria-label");
            }
            if (nomeJogo.isBlank()) {
                nomeJogo = elem.text();
            }

            String imagemUrl = elem.attr("data-bgset");
            if (imagemUrl.isBlank()) {
                imagemUrl = elem.attr("data-src");
            }

            String linkPagina = elem.attr("href");

            if (!nomeJogo.isBlank() && !imagemUrl.isBlank()) {
                jogos.add(new JogoScrapedDTO(
                    nomeJogo.trim(), 
                    imagemUrl.trim(), 
                    linkPagina.trim()
                ));
            }
        }

        return jogos;
    }

    public List<JogoScrapedDTO> buscarJogosDoSite() throws IOException {
        List<JogoScrapedDTO> jogos = new ArrayList<>();

        // Faz a requisição HTTP imitando um navegador (User-Agent) para evitar bloqueios
        Document doc = Jsoup.connect(URL_TARGET)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                .timeout(10000)
                .get();

        // Seleciona os links <a> que possuem a classe 'penci-image-holder'
        Elements elementos = doc.select("a.penci-image-holder");

        for (Element elem : elementos) {
            // Extrai o título do atributo 'title'
            String nome = elem.attr("title");

            // Extrai o link da imagem (busca primeiro no data-bgset, depois no atributo style como fallback)
            String imagemUrl = elem.attr("data-bgset");
            if (imagemUrl.isEmpty()) {
                imagemUrl = elem.attr("data-src");
            }

            // Extrai o link da página do jogo
            String linkPagina = elem.attr("href");

            // Se encontrou ao menos o nome do jogo, adiciona na lista
            if (nome != null && !nome.isBlank()) {
                jogos.add(new JogoScrapedDTO(nome.trim(), imagemUrl, linkPagina));
            }
        }

        return jogos;
    }
}