package dev.hugofaria.med.voll.api.domain.consulta.validacoes.agendamento;

import dev.hugofaria.med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import dev.hugofaria.med.voll.api.domain.ValidacaoException;
import dev.hugofaria.med.voll.api.domain.paciente.PacienteRepository;
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
