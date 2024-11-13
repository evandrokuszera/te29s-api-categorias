package br.edu.utfpr.api_categoria.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CategoriaDTO(
        Long id,

        @NotNull(message = "Name é obrigatório")
        @NotBlank(message = "Name é obrigatório")
        @Size(min=3, max=50, message = "Name deve ter entre 3 e 50 caracteres")
        String name,

        @NotNull(message = "Active é obrigatório")
        Boolean active
) {}