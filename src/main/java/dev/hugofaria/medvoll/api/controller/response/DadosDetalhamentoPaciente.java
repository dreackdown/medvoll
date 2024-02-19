package dev.hugofaria.medvoll.api.controller.response;

import dev.hugofaria.medvoll.domain.model.Endereco;
import dev.hugofaria.medvoll.domain.model.Paciente;

public record DadosDetalhamentoPaciente(Long id, String nome, String email, String cpf, String telefone,
                                        Endereco endereco) {

    public DadosDetalhamentoPaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }
}