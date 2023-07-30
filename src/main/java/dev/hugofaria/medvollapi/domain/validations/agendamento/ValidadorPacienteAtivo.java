package dev.hugofaria.medvollapi.domain.validations.agendamento;

import dev.hugofaria.medvollapi.controller.request.DadosAgendamentoConsulta;
import dev.hugofaria.medvollapi.infra.exception.ValidacaoException;
import dev.hugofaria.medvollapi.domain.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository repository;

    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído");
        }
    }
}
