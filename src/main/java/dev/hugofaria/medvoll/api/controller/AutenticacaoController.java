package dev.hugofaria.medvoll.api.controller;

import dev.hugofaria.medvoll.api.controller.request.DadosAutenticacao;
import dev.hugofaria.medvoll.domain.model.Usuario;
import dev.hugofaria.medvoll.infra.security.DadosTokenJWT;
import dev.hugofaria.medvoll.infra.security.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class AutenticacaoController {

    private final AuthenticationManager manager;

    private final TokenService tokenService;

    @Operation(summary = "Autenticação do Usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário autenticado, retorna token",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = DadosTokenJWT.class))}),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<DadosTokenJWT> login(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}