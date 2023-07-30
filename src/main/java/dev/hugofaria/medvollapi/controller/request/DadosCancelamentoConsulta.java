package dev.hugofaria.medvollapi.controller.request;

import jakarta.validation.constraints.NotNull;
import dev.hugofaria.medvollapi.domain.model.enums.MotivoCancelamento;

public record DadosCancelamentoConsulta(
        @NotNull
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivo) {
}