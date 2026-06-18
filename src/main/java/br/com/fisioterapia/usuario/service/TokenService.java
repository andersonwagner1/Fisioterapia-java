package br.com.fisioterapia.usuario.service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.fisioterapia.usuario.model.Usuario;
@Service
public class TokenService {

    // Chave secreta que SÓ o backend conhece
    private final String SECRET_KEY = "minha_chave_secreta_super_segura_com_mais_de_32_caracteres"; 
    
    // Tempo de expiração: 1 hora (em milissegundos)
    private final long EXPIRATION_TIME = 3600000; 

    public String gerarToken(Usuario usuario) {
        return Jwts.builder()
                .setSubject(usuario.getEmail()) // Define o 'sub' (identificador)
                .claim("name", usuario.getNome()) // Claim customizada
                .claim("roles", usuario.getPermissoesTelas()) // Roles do usuário
                .setIssuedAt(new Date()) // Data de criação
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Define o 'exp'
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY.getBytes()) // Assina com a chave secreta
                .compact(); // Transforma tudo na String JWT (aquela com os 3 pontos)
    }
}