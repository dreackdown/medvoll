package dev.hugofaria.medvoll.api.controller.response;

import dev.hugofaria.medvoll.domain.model.enums.Especialidade;
import dev.hugofaria.medvoll.domain.model.Medico;

public record DadosListagemMedico(Long id, String nome, String email, String crm, Especialidade especialidade) {

    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}