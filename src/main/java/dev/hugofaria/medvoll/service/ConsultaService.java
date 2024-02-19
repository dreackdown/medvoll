package dev.hugofaria.medvoll.service;

import dev.hugofaria.medvoll.api.controller.request.DadosAgendamentoConsulta;
import dev.hugofaria.medvoll.api.controller.request.DadosCancelamentoConsulta;
import dev.hugofaria.medvoll.api.controller.response.DadosDetalhamentoConsulta;
import dev.hugofaria.medvoll.domain.model.Consulta;
import dev.hugofaria.medvoll.domain.model.Medico;
import dev.hugofaria.medvoll.domain.repository.ConsultaRepository;
import dev.hugofaria.medvoll.domain.repository.MedicoRepository;
import dev.hugofaria.medvoll.domain.repository.PacienteRepository;
import dev.hugofaria.medvoll.domain.validations.agendamento.ValidadorAgendamentoDeConsulta;
import dev.hugofaria.medvoll.domain.validations.cancelamento.ValidadorCancelamentoDeConsulta;
import dev.hugofaria.medvoll.infra.exception.ValidacaoException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;

    private final MedicoRepository medicoRepository;

    private final PacienteRepository pacienteRepository;

    private List<ValidadorAgendamentoDeConsulta> validadores;

    private List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            throw new ValidacaoException("Id do paciente informado não existe!");
        }

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados));

        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        if (medico == null) {
            throw new ValidacaoException("Não existe médico disponível nessa data!");
        }

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);
        consultaRepository.save(consulta);

        return new DadosDetalhamentoConsulta(consulta);
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        if (!consultaRepository.existsById(dados.idConsulta())) {
            throw new ValidacaoException("Id da consulta informado não existe!");
        }

        validadoresCancelamento.forEach(v -> v.validar(dados));

        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        consulta.cancelar(dados.motivo());
    }


    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if (dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());
    }
}