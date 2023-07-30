package dev.hugofaria.medvollapi.domain.validations.cancelamento;

import dev.hugofaria.medvollapi.controller.request.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsulta dados);

}
