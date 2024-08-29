package dev.hugofaria.med.voll.api.controller;

import dev.hugofaria.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import dev.hugofaria.med.voll.api.domain.consulta.DadosCancelamentoConsulta;
import dev.hugofaria.med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import dev.hugofaria.med.voll.api.domain.consulta.AgendaDeConsultas;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consulta")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    private final AgendaDeConsultas agenda;

    public ConsultaController(AgendaDeConsultas agenda) {
        this.agenda = agenda;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DadosDetalhamentoConsulta> agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        var dto = agenda.agendar(dados);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<Void> cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }
}