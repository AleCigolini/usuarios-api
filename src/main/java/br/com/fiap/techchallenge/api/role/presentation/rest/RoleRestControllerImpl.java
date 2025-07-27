package br.com.fiap.techchallenge.api.role.presentation.rest;

import br.com.fiap.techchallenge.api.role.application.controller.RoleController;
import br.com.fiap.techchallenge.api.role.common.domain.dto.request.RoleRequestDto;
import br.com.fiap.techchallenge.api.role.common.domain.dto.response.RoleResponseDto;
import br.com.fiap.techchallenge.api.role.presentation.rest.interfaces.RoleRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleRestControllerImpl implements RoleRestController {
    private final RoleController usuarioController;

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public RoleResponseDto cadastrarRole(@RequestBody RoleRequestDto roleRequestDto) {
        return usuarioController.cadastrarRole(roleRequestDto);
    }

    @Override
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<RoleResponseDto> buscarRoles() {
        return usuarioController.consultarRoles();
    }

    @Override
    @GetMapping("/{role}")
    @ResponseStatus(HttpStatus.OK)
    public RoleResponseDto buscarRolePorNome(@PathVariable("role") String role) {
        return usuarioController.buscarRolePorNome(role);
    }

    @Override
    @PutMapping("/{role}/habilitar")
    @ResponseStatus(HttpStatus.OK)
    public RoleResponseDto habilitarRolePorNome(@PathVariable("role") String role) {
        return usuarioController.habilitarRolePorNome(role);
    }

    @Override
    @DeleteMapping("/{role}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluirRolePorNome(@PathVariable("role") String role) {
        usuarioController.excluirRolePorNome(role);
    }
}
