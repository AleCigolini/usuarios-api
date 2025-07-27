package br.com.fiap.techchallenge.api.usuario.application.mapper.modelmapper;

import br.com.fiap.techchallenge.api.usuario.application.mapper.RequestUsuarioMapper;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.UsuarioRequestDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.IdentificarUsuarioDto;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import br.com.fiap.techchallenge.api.core.config.exception.exceptions.ValidacaoEntidadeException;
import lombok.AllArgsConstructor;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RequestUsuarioModelMapper implements RequestUsuarioMapper {
    private ModelMapper modelMapper;

    public Usuario requestDtoToDomain(UsuarioRequestDto usuarioRequestDto) {
        try {
            return modelMapper.map(usuarioRequestDto, Usuario.class);
        } catch (MappingException e) {
            if (e.getCause() instanceof ValidacaoEntidadeException) {
                throw (ValidacaoEntidadeException) e.getCause();
            }
            throw e;
        }
    }

    @Override
    public Usuario requestDtoToDomain(IdentificarUsuarioDto identificarUsuarioDto) {
        try {
            return modelMapper.map(identificarUsuarioDto, Usuario.class);
        } catch (MappingException e) {
            if (e.getCause() instanceof ValidacaoEntidadeException) {
                throw (ValidacaoEntidadeException) e.getCause();
            }
            throw e;
        }
    }
}
