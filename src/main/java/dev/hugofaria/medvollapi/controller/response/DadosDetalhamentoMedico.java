package dev.hugofaria.medvollapi.controller.response;

import dev.hugofaria.medvollapi.domain.model.enums.Especialidade;
import dev.hugofaria.medvollapi.domain.model.Endereco;
import dev.hugofaria.medvollapi.domain.model.Medico;

public record DadosDetalhamentoMedico(Long id, String nome, String email, String crm, String telefone,
                                      Especialidade especialidade,
                                      Endereco endereco) {

    public DadosDetalhamentoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getTelefone(), medico.getEspecialidade(), medico.getEndereco());
    }
}