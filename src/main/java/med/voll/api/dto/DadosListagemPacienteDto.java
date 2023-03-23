package med.voll.api.dto;

import med.voll.api.entities.Paciente;

public record DadosListagemPacienteDto(Long id, String nome, String email, String cpf) {

    public DadosListagemPacienteDto(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }
}
