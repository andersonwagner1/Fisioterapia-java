package br.com.fisioterapia.usuario.dto;

import br.com.fisioterapia.usuario.model.Usuario;

public record UsuarioRespostaDTO(
    Long id,
    String nome,
    String email,
    String funcao,
    Boolean ativo,
    String crefitoNumero
) {
    // Construtor compacto para mapeamento rápido da entidade
    public UsuarioRespostaDTO(Usuario usuario) {
        this(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getFuncao(),
            usuario.getAtivo(),
            usuario.getCrefitoNumero()
        );
    }
}