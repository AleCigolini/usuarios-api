package br.com.fiap.techchallenge.api.usuario.presentation.rest.interfaces;

import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.UsuarioRequestDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.IdentificarUsuarioDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.response.UsuarioResponseDto;
import br.com.fiap.techchallenge.api.core.utils.validators.cpf.Cpf;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

@Tag(name = "${tag.swagger.usuario.name}", description = "${tag.swagger.usuario.description}")
public interface UsuarioRestController {

    /**
     * Busca o usuario a partir de seu CPF.
     *
     * @param cpf String do cpf do usuario
     * @return {@link ResponseEntity< UsuarioResponseDto >}
     */
    @Operation(summary = "Buscar usuario a partir de seu cpf",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Encontrado mais de um usuario para o mesmo cpf/Erros de validação",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Usuario não encntrado",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    )
            })
    UsuarioResponseDto buscarUsuarioPorCpf(@Parameter(description = "CPF válido do usuario", example = "52932609017", required = true)  @Valid @Cpf String cpf);

    /**
     * Busca o usuario a partir de seu e-mail.
     *
     * @param email String do e-mail do usuario
     * @return {@link ResponseEntity< UsuarioResponseDto >}
     */
    @Operation(summary = "Buscar usuario a partir de seu e-mail",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Encontrado mais de um usuario para o mesmo e-mail/Erros de validação",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Usuario não encntrado",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    )
            })
    UsuarioResponseDto buscarUsuarioPorEmail(@Parameter(description = "E-mail válido do usuario", example = "email@email.com", required = true) @Valid String email);

    /**
     * Busca o usuario a partir de seu id.
     *
     * @param id UUID do id do usuario
     * @return {@link ResponseEntity< UsuarioResponseDto >}
     */
    @Operation(summary = "Buscar usuario a partir de seu id",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "404", description = "Usuario não encntrado",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    )
            })
    UsuarioResponseDto buscarUsuarioPorId(@Parameter(description = "ID do usuario", example = "123e4567-e89b-12d3-a456-426655440000", required = true) UUID id);

    /**
     * Cadastrar um novo usuario.
     *
     * @param usuarioRequestDto objeto contendo os dados para cadastro do usuario, possuindo cpf ou e-mail obrigatóriamente e de maneira válida
     * @return {@link ResponseEntity< UsuarioResponseDto >}
     */
    @Operation(summary = "Cadastrar usuario",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Erro no cadastro do usuario/Erros de validação",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
    })
    UsuarioResponseDto cadastrarUsuario(@Valid UsuarioRequestDto usuarioRequestDto);

    /**
     * Cadastrar um novo usuario admin.
     *
     * @param usuarioRequestDto objeto contendo os dados para cadastro do usuario, possuindo cpf ou e-mail obrigatóriamente e de maneira válida
     * @return {@link ResponseEntity< UsuarioResponseDto >}
     */
    @Operation(summary = "Cadastrar usuario",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Erro no cadastro do usuario/Erros de validação",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
            })
    UsuarioResponseDto cadastrarUsuarioAdmin(@Valid UsuarioRequestDto usuarioRequestDto);

    /**
     * Identificar um usuario a partir de seu CPF ou e-mail.
     *
     * @param identificarUsuarioDto objeto contendo os dados para a identificação do usuario, possuindo cpf obrigatóriamente e de maneira válida
     * @return {@link ResponseEntity< UsuarioResponseDto >}
     */
    @Operation(summary = "Identificar usuario",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Erro na identificação do usuario/Erros de validação",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
            })
    UsuarioResponseDto identificarUsuario(@Valid IdentificarUsuarioDto identificarUsuarioDto);

}
