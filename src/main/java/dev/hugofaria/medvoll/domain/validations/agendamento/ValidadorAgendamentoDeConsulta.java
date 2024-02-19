package dev.hugofaria.medvoll.domain.validations.agendamento;

import dev.hugofaria.medvoll.api.controller.request.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {

    void validar(DadosAgendamentoConsulta dados);

}
