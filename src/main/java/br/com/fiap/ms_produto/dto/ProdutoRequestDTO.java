package br.com.fiap.ms_produto.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.util.Set;

public record ProdutoRequestDTO(
        @NotBlank(message = "Campo nome requerido!")
        @Size(min = 3, max = 100, message = "O nome deve ter de 3 a 100 caracteres")
        String nome,

        @NotBlank(message = "Campo descrição requerido!")
        @Size(min = 10, message = "A descrição deve ter no mínimo 10 caracteres")
        String descricao,

        @NotNull(message = "Campo valor requerido")
        @Positive(message = "O preço deve ser um valor positivo maior que zero")
        Double valor,

        @NotNull(message = "Campo Categoria requerido")
        CategoriaDTO categoria,

        @NotNull(message = "Campo Lojas requerido")
        Set<LojaDTO> lojas) // lojas associadas
{

}
