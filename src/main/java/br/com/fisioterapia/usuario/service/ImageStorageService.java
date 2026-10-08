package br.com.fisioterapia.usuario.service;



import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.util.UUID;

@Service
public class ImageStorageService {

    // Pasta onde as imagens serão salvas no servidor
    private static final String PASTA_UPLOADS = "uploads/capas/";

    public String baixarESalvarImagem(String urlImagem) throws IOException {
        // 1. Cria o diretório se ele ainda não existir
        Path pastaDestino = Paths.get(PASTA_UPLOADS);
        if (!Files.exists(pastaDestino)) {
            Files.createDirectories(pastaDestino);
        }

        // 2. Extrai a extensão da imagem (.jpg, .png, etc)
        String extensao = ".jpg";
        if (urlImagem.contains(".")) {
            extensao = urlImagem.substring(urlImagem.lastIndexOf("."));
            if (extensao.contains("?")) {
                extensao = extensao.substring(0, extensao.indexOf("?")); // Remove parâmetros de URL se houver
            }
        }

        // 3. Gera um nome único para o arquivo para evitar sobreposição (ex: a1b2c3d4.jpg)
        String nomeArquivoUnico = UUID.randomUUID().toString() + extensao;
        Path caminhoCompleto = pastaDestino.resolve(nomeArquivoUnico);

        // 4. Faz o download do array de bytes da imagem via Jsoup
        Connection.Response response = Jsoup.connect(urlImagem)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64)")
                .ignoreContentType(true)
                .execute();

        byte[] bytesImagem = response.bodyAsBytes();

        // 5. Escreve os bytes no arquivo local no disco
        Files.write(caminhoCompleto, bytesImagem, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

        // Retorna apenas o nome do arquivo gerado para salvar no banco de dados
        return nomeArquivoUnico;
    }
}