package dev.hugofaria.med.voll.api.domain.consulta.validacoes.cancelamento;

import dev.hugofaria.med.voll.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {

    void validar(DadosCancelamentoConsulta dados);

}
