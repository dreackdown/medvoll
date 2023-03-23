package med.voll.api.dto;

import med.voll.api.entities.Endereco;
import med.voll.api.entities.Paciente;

public record DadosDetalhamentoPacienteDto(Long id, String nome, String email, String cpf, String telefone,
                                           Endereco endereco) {

    public DadosDetalhamentoPacienteDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf(), paciente.getTelefone(), paciente.getEndereco());
    }
}
