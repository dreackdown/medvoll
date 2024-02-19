package dev.hugofaria.medvoll.api.controller;

import dev.hugofaria.medvoll.api.controller.request.DadosAgendamentoConsulta;
import dev.hugofaria.medvoll.api.controller.request.DadosCancelamentoConsulta;
import dev.hugofaria.medvoll.api.controller.response.DadosDetalhamentoConsulta;
import dev.hugofaria.medvoll.service.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consulta")
@SecurityRequirement(name = "bearer-key")
@AllArgsConstructor
public class ConsultaController {

    private final ConsultaService agenda;

    @Operation(summary = "Agendar consulta")
    @ApiResponses(value =
    @ApiResponse(responseCode = "200", description = "Consulta agendada",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = DadosDetalhamentoConsulta.class))}))
    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @Operation(summary = "Cancelar consulta")
    @ApiResponses(value = @ApiResponse(responseCode = "204", description = "Consulta cancelada"))
    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}