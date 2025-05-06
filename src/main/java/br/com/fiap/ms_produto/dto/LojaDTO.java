package br.com.fiap.ms_produto.dto;

import br.com.fiap.ms_produto.entities.Loja;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LojaDTO {

    private Long id;

    @NotBlank(message = "Nome da Loja é requerido")
    @Size(min = 3, max = 100, message = "O nome da loja deve ter entre 3 e 100 caracteres")
    private String nome;

    public LojaDTO(Loja entity) {
        id = entity.getId();
        nome = entity.getNome();
    }
}
