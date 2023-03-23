package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.DadosEnderecoDto;

public record DadosAtualizacaoMedicoDto(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEnderecoDto endereco) {
}
