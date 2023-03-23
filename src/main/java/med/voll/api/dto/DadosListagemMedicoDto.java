package med.voll.api.dto;

import med.voll.api.entities.Medico;
import med.voll.api.entities.enums.Especialidade;

public record DadosListagemMedicoDto(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedicoDto(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
