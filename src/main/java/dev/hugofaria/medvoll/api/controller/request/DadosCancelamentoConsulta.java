package dev.hugofaria.medvoll.api.controller.request;

import jakarta.validation.constraints.NotNull;
import dev.hugofaria.medvoll.domain.model.enums.MotivoCancelamento;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo) {
}