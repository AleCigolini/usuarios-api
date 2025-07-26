package br.com.fiap.techchallenge.api.usuario.application.mapper;

import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.UsuarioRequestDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.IdentificarUsuarioDto;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;

public interface RequestUsuarioMapper {
    Usuario requestDtoToDomain(UsuarioRequestDto usuarioRequestDto);
    Usuario requestDtoToDomain(IdentificarUsuarioDto identificarUsuarioDto);
}
