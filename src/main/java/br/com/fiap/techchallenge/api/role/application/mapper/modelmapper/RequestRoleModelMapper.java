package br.com.fiap.techchallenge.api.role.application.mapper.modelmapper;

import br.com.fiap.techchallenge.api.core.config.exception.exceptions.ValidacaoEntidadeException;
import br.com.fiap.techchallenge.api.role.application.mapper.RequestRoleMapper;
import br.com.fiap.techchallenge.api.role.common.domain.dto.request.RoleRequestDto;
import br.com.fiap.techchallenge.api.role.domain.Role;
import lombok.AllArgsConstructor;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RequestRoleModelMapper implements RequestRoleMapper {
    private ModelMapper modelMapper;

    @Override
    public Role requestDtoToDomain(RoleRequestDto roleRequestDto) {
        try {
            return modelMapper.map(roleRequestDto, Role.class);
        } catch (MappingException e) {
            if (e.getCause() instanceof ValidacaoEntidadeException) {
                throw (ValidacaoEntidadeException) e.getCause();
            }
            throw e;
        }
    }
}
