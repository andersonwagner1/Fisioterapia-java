package br.com.fisioterapia.usuario.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record JogoDTO(
    Long id,

    @NotBlank(message = "O nome do jogo é obrigatório")
    String nome,

    @NotBlank(message = "Informe 'S' ou 'N'")
    //@Pattern(regexp = "[SN]", message = "O campo jogado deve ser 'S' ou 'N'")
    String jogado,

    String observacao,

    String categoria,

    @Min(value =0, message = "Vontade de jogar deve ser no mínimo 1")
    @Max(value = 10, message = "Vontade de jogar deve ser no máximo 10")
    Integer vontadeJogar
) {}