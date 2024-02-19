package dev.hugofaria.medvoll.domain.validations.cancelamento;

import dev.hugofaria.medvoll.api.controller.request.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsulta dados);

}
