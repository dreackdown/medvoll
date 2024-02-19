package dev.hugofaria.medvoll.api.controller.response;

import dev.hugofaria.medvoll.domain.model.enums.Especialidade;
import dev.hugofaria.medvoll.domain.model.Endereco;
import dev.hugofaria.medvoll.domain.model.Medico;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone,
                                      Especialidade especialidade,
                                      Endereco endereco) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}