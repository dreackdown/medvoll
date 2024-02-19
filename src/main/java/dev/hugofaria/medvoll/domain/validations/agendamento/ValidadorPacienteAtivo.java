package dev.hugofaria.medvoll.domain.validations.agendamento;

import dev.hugofaria.medvoll.api.controller.request.DadosAgendamentoConsulta;
import dev.hugofaria.medvoll.domain.repository.PacienteRepository;
import dev.hugofaria.medvoll.infra.exception.ValidacaoException;
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
