package br.com.fiap.techchallenge.api.usuario.presentation.rest;

import br.com.fiap.techchallenge.api.usuario.application.controller.UsuarioController;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.UsuarioRequestDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.IdentificarUsuarioDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.response.UsuarioResponseDto;
import br.com.fiap.techchallenge.api.usuario.presentation.rest.interfaces.UsuarioRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioRestControllerImpl implements UsuarioRestController {
    private final UsuarioController usuarioController;

    @Override
    @GetMapping("/cpf")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDto buscarUsuarioPorCpf(@RequestHeader("CPF") String cpf) {
        return usuarioController.buscarUsuarioPorCpf(cpf);
    }

    @Override
    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDto buscarUsuarioPorEmail(@RequestHeader("EMAIL") String email) {
        return usuarioController.buscarUsuarioPorEmail(email);
    }

    @Override
    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDto buscarUsuarioPorId(@RequestHeader("ID") UUID id) {
        return usuarioController.buscarUsuarioPorId(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDto cadastrarUsuario(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        return usuarioController.cadastrarUsuario(usuarioRequestDto);
    }

    @Override
    @PostMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDto cadastrarUsuarioAdmin(@RequestBody UsuarioRequestDto usuarioRequestDto) {
        return usuarioController.cadastrarUsuarioAdmin(usuarioRequestDto);
    }

    @Override
    @PostMapping("/identificacao")
    @ResponseStatus(HttpStatus.OK)
    public UsuarioResponseDto identificarUsuario(@RequestBody IdentificarUsuarioDto identificarUsuarioDto) {
        return usuarioController.identificarUsuario(identificarUsuarioDto);
    }

}
