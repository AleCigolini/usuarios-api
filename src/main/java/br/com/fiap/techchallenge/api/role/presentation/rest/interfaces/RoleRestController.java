package br.com.fiap.techchallenge.api.role.presentation.rest.interfaces;

import br.com.fiap.techchallenge.api.role.common.domain.dto.request.RoleRequestDto;
import br.com.fiap.techchallenge.api.role.common.domain.dto.response.RoleResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "${tag.swagger.role.name}", description = "${tag.swagger.role.description}")
public interface RoleRestController {

    /**
     * Cadastra uma nova role.
     * @param roleRequestDto objeto contendo os dados para cadastro da role, possuindo nome e descrição obrigatóriamente
     *
     * @return {@link ResponseEntity<RoleResponseDto>}
     */
    @Operation(summary = "Cadastra uma nova role",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Encontrada mais de uma role para o mesmo nome/Erros de validação",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    )
            })
    RoleResponseDto cadastrarRole(@Valid RoleRequestDto roleRequestDto);

    /**
     * Busca uma role a partir do seu nome.
     *
     * @param role String da role
     * @return {@link ResponseEntity<RoleResponseDto>}
     */
    @Operation(summary = "Buscar role a partir do seu nome",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Encontrado mais de uma role para o mesmo nome/Erros de validação",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Role não encontrada",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    )
            })
    RoleResponseDto buscarRolePorNome(@Parameter(description = "Role que estã sendo buscada", example = "ADMIN", required = true) @Valid String role);

    /**
     * Buscar todas as roles.
     *
     * @return lista de {@link RoleResponseDto}
     */
    @Operation(summary = "Buscar todas as roles",
            responses = {
                    @ApiResponse(responseCode = "200"),
            })
    List<RoleResponseDto> buscarRoles();

    /**
     * Habilita uma role a partir do seu nome.
     *
     * @param role String da role
     * @return {@link ResponseEntity<RoleResponseDto>}
     */
    @Operation(summary = "Habilita uma role a partir do seu nome",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Erro na exclusão da role/Erros de validação",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Role não encontrada",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    )
            })
    RoleResponseDto habilitarRolePorNome(@Parameter(description = "Role que está sendo buscada", example = "ADMIN", required = true) @Valid String role);

    /**
     * Exclui uma role a partir do seu nome.
     *
     * @param role String da role
     */
    @Operation(summary = "Exclui uma role a partir do seu nome",
            responses = {
                    @ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "400", description = "Erro na exclusão da role/Erros de validação",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    ),
                    @ApiResponse(responseCode = "404", description = "Role não encontrada",
                            content = @Content(schema = @Schema(ref = "Problema"))
                    )
            })
    void excluirRolePorNome(@Parameter(description = "Role que está sendo buscada", example = "ADMIN", required = true) @Valid String role);

}
