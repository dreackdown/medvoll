package dev.hugofaria.medvollapi.domain.validations.agendamento;

import dev.hugofaria.medvollapi.controller.request.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsulta dados);

}
