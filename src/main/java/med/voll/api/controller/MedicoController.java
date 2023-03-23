package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosAtualizacaoMedicoDto;
import med.voll.api.dto.DadosCadastroMedicoDto;
import med.voll.api.dto.DadosDetalhamentoMedicoDto;
import med.voll.api.dto.DadosListagemMedicoDto;
import med.voll.api.entities.Medico;
import med.voll.api.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository medicoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedicoDto dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        medicoRepository.save(medico);

        var uri = uriBuilder.path("/medico/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDto(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedicoDto>> findAll(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = medicoRepository.findAllByAtivoTrue(paginacao).map(DadosListagemMedicoDto::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualiza(@RequestBody @Valid DadosAtualizacaoMedicoDto dados) {
        var medico = medicoRepository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedicoDto(medico));
    }

    @DeleteMapping("/excluir/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        var medico = medicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedicoDto(medico));
    }
}
