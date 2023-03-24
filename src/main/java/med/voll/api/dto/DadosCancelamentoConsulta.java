package med.voll.api.dto;

import jakarta.validation.constraints.NotNull;
import med.voll.api.entities.enums.MotivoCancelamento;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo) {
}
