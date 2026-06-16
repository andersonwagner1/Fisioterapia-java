package br.com.fisioterapia.usuario.service;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDTO(
    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 150)
    String nome,

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "Formato de e-mail inválido")
    String email,

    @NotBlank(message = "O CPF é obrigatório")
    String cpf,

    String telefone,

    @NotBlank(message = "A função/cargo é obrigatória")
    String funcao,

    String crefitoNumero,
    String crefitoUf,
    Integer tempoSessaoMinutos
) {}