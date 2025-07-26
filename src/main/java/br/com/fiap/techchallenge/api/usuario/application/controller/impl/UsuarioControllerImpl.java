package br.com.fiap.techchallenge.api.usuario.application.controller.impl;

import br.com.fiap.techchallenge.api.usuario.application.controller.UsuarioController;
import br.com.fiap.techchallenge.api.usuario.application.gateway.AuthenticationGateway;
import br.com.fiap.techchallenge.api.usuario.application.gateway.UsuarioGateway;
import br.com.fiap.techchallenge.api.usuario.application.gateway.impl.AuthenticationGatewayImpl;
import br.com.fiap.techchallenge.api.usuario.application.gateway.impl.UsuarioGatewayImpl;
import br.com.fiap.techchallenge.api.usuario.application.mapper.DatabaseUsuarioMapper;
import br.com.fiap.techchallenge.api.usuario.application.mapper.RequestUsuarioMapper;
import br.com.fiap.techchallenge.api.usuario.application.presenter.UsuarioPresenter;
import br.com.fiap.techchallenge.api.usuario.application.usecase.ConsultarUsuarioUseCase;
import br.com.fiap.techchallenge.api.usuario.application.usecase.SalvarUsuarioUseCase;
import br.com.fiap.techchallenge.api.usuario.application.usecase.impl.ConsultarUsuarioUseCaseImpl;
import br.com.fiap.techchallenge.api.usuario.application.usecase.impl.SalvarUsuarioUseCaseImpl;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.UsuarioRequestDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.IdentificarUsuarioDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.response.UsuarioResponseDto;
import br.com.fiap.techchallenge.api.usuario.common.interfaces.UsuarioAuthentication;
import br.com.fiap.techchallenge.api.usuario.common.interfaces.UsuarioDatabase;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class UsuarioControllerImpl implements UsuarioController {
    private final SalvarUsuarioUseCase salvarUsuarioUseCase;
    private final ConsultarUsuarioUseCase consultarUsuarioUseCase;

    private final RequestUsuarioMapper requestUsuarioMapper;
    private final UsuarioPresenter usuarioPresenter;

    public UsuarioControllerImpl(UsuarioDatabase usuarioDatabase, DatabaseUsuarioMapper mapper, RequestUsuarioMapper requestUsuarioMapper, UsuarioPresenter usuarioPresenter, UsuarioAuthentication usuarioAuthentication) {
        this.requestUsuarioMapper = requestUsuarioMapper;
        this.usuarioPresenter = usuarioPresenter;
        final UsuarioGateway usuarioGateway = new UsuarioGatewayImpl(usuarioDatabase, mapper);
        final AuthenticationGateway authenticationGateway = new AuthenticationGatewayImpl(usuarioAuthentication);
        this.salvarUsuarioUseCase = new SalvarUsuarioUseCaseImpl(usuarioGateway, authenticationGateway);
        this.consultarUsuarioUseCase = new ConsultarUsuarioUseCaseImpl(usuarioGateway);
    }

    @Override
    public UsuarioResponseDto buscarUsuarioPorCpf(String cpf) {
        return usuarioPresenter.toResponse(consultarUsuarioUseCase.buscarUsuarioPorCpf(new Cpf(cpf)));
    }

    @Override
    public UsuarioResponseDto buscarUsuarioPorEmail(String email) {
        return usuarioPresenter.toResponse(consultarUsuarioUseCase.buscarUsuarioPorEmail(new Email(email)));
    }

    @Override
    public UsuarioResponseDto buscarUsuarioPorId(UUID id) {
        return usuarioPresenter.toResponse(consultarUsuarioUseCase.buscarUsuarioPorId(id));
    }

    @Override
    public UsuarioResponseDto cadastrarUsuario(UsuarioRequestDto usuarioRequestDto) {
        return usuarioPresenter.toResponse(salvarUsuarioUseCase.salvarUsuario(requestUsuarioMapper.requestDtoToDomain(usuarioRequestDto)));
    }

    @Override
    public UsuarioResponseDto identificarUsuario(IdentificarUsuarioDto identificarUsuarioDto) {
        Usuario usuarioParametros = requestUsuarioMapper.requestDtoToDomain(identificarUsuarioDto);
        Usuario usuarioExistente = consultarUsuarioUseCase.buscarUsuarioPorEmailCpf(usuarioParametros.getCpf(), usuarioParametros.getEmail(), false);
        if (usuarioExistente != null) {
            return usuarioPresenter.toResponse(usuarioExistente);
        }
        return usuarioPresenter.toResponse(salvarUsuarioUseCase.salvarUsuario(usuarioParametros));
    }
}
