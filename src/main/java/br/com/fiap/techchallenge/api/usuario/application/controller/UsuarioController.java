package br.com.fiap.techchallenge.api.usuario.application.controller;

import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.UsuarioRequestDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.IdentificarUsuarioDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.response.UsuarioResponseDto;

import java.util.UUID;

public interface UsuarioController {
    UsuarioResponseDto buscarUsuarioPorCpf(String cpf);
    UsuarioResponseDto buscarUsuarioPorEmail(String email);
    UsuarioResponseDto buscarUsuarioPorId(UUID id);
    UsuarioResponseDto cadastrarUsuario(UsuarioRequestDto usuarioRequestDto);
    UsuarioResponseDto cadastrarUsuarioAdmin(UsuarioRequestDto usuarioRequestDto);
    UsuarioResponseDto identificarUsuario(IdentificarUsuarioDto identificarUsuarioDto);
}
