package br.com.fiap.techchallenge.api.usuario.application.controller.impl;

import br.com.fiap.techchallenge.api.core.utils.domain.Cpf;
import br.com.fiap.techchallenge.api.core.utils.domain.Email;
import br.com.fiap.techchallenge.api.role.application.gateway.RoleGateway;
import br.com.fiap.techchallenge.api.role.application.gateway.impl.RoleGatewayImpl;
import br.com.fiap.techchallenge.api.role.application.mapper.DatabaseRoleMapper;
import br.com.fiap.techchallenge.api.role.application.usecase.ConsultarRoleUseCase;
import br.com.fiap.techchallenge.api.role.application.usecase.impl.ConsultarRoleUseCaseImpl;
import br.com.fiap.techchallenge.api.role.common.interfaces.RoleDatabase;
import br.com.fiap.techchallenge.api.role.domain.Role;
import br.com.fiap.techchallenge.api.usuario.application.controller.UsuarioController;
import br.com.fiap.techchallenge.api.usuario.application.gateway.UsuarioGateway;
import br.com.fiap.techchallenge.api.usuario.application.gateway.impl.UsuarioGatewayImpl;
import br.com.fiap.techchallenge.api.usuario.application.mapper.DatabaseUsuarioMapper;
import br.com.fiap.techchallenge.api.usuario.application.mapper.RequestUsuarioMapper;
import br.com.fiap.techchallenge.api.usuario.application.presenter.UsuarioPresenter;
import br.com.fiap.techchallenge.api.usuario.application.usecase.ConsultarUsuarioUseCase;
import br.com.fiap.techchallenge.api.usuario.application.usecase.SalvarUsuarioUseCase;
import br.com.fiap.techchallenge.api.usuario.application.usecase.ValidarSenhaUsuarioUseCase;
import br.com.fiap.techchallenge.api.usuario.application.usecase.impl.ConsultarUsuarioUseCaseImpl;
import br.com.fiap.techchallenge.api.usuario.application.usecase.impl.SalvarUsuarioUseCaseImpl;
import br.com.fiap.techchallenge.api.usuario.application.usecase.impl.ValidarSenhaUsuarioUseCaseImpl;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.IdentificarUsuarioDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.request.UsuarioRequestDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.dto.response.UsuarioResponseDto;
import br.com.fiap.techchallenge.api.usuario.common.domain.exception.UsuarioValidacaoException;
import br.com.fiap.techchallenge.api.usuario.common.interfaces.UsuarioDatabase;
import br.com.fiap.techchallenge.api.usuario.domain.Usuario;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.util.UUID;
import java.util.logging.Logger;

@Controller
public class UsuarioControllerImpl implements UsuarioController {
    private final SalvarUsuarioUseCase salvarUsuarioUseCase;
    private final ConsultarUsuarioUseCase consultarUsuarioUseCase;
    private final ValidarSenhaUsuarioUseCase validarSenhaUsuarioUseCase;
    private final ConsultarRoleUseCase consultarRoleUseCase;

    private final RequestUsuarioMapper requestUsuarioMapper;
    private final UsuarioPresenter usuarioPresenter;
    private static final Logger LOGGER = Logger.getLogger(UsuarioControllerImpl.class.getName());


    public UsuarioControllerImpl(UsuarioDatabase usuarioDatabase, DatabaseUsuarioMapper mapper, RequestUsuarioMapper requestUsuarioMapper,
                                 UsuarioPresenter usuarioPresenter, RoleDatabase roleDatabase, DatabaseRoleMapper databaseRoleMapper,
                                 PasswordEncoder passwordEncoder) {
        this.requestUsuarioMapper = requestUsuarioMapper;
        this.usuarioPresenter = usuarioPresenter;
        final UsuarioGateway usuarioGateway = new UsuarioGatewayImpl(usuarioDatabase, mapper, databaseRoleMapper);
        final RoleGateway roleGateway = new RoleGatewayImpl(roleDatabase, databaseRoleMapper);
        this.salvarUsuarioUseCase = new SalvarUsuarioUseCaseImpl(usuarioGateway, passwordEncoder);
        this.consultarUsuarioUseCase = new ConsultarUsuarioUseCaseImpl(usuarioGateway);
        this.validarSenhaUsuarioUseCase = new ValidarSenhaUsuarioUseCaseImpl(passwordEncoder);
        this.consultarRoleUseCase = new ConsultarRoleUseCaseImpl(roleGateway);
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
        Role role = consultarRoleUseCase.buscarRolePorNome("USUARIO");

        return usuarioPresenter.toResponse(salvarUsuarioUseCase.salvarUsuario(requestUsuarioMapper.requestDtoToDomain(usuarioRequestDto), role));
    }

    @Override
    public UsuarioResponseDto cadastrarUsuarioAdmin(UsuarioRequestDto usuarioRequestDto) {
        Role role = consultarRoleUseCase.buscarRolePorNome("ADMIN");

        return usuarioPresenter.toResponse(salvarUsuarioUseCase.salvarUsuario(requestUsuarioMapper.requestDtoToDomain(usuarioRequestDto), role));
    }

    @Override
    public UsuarioResponseDto identificarUsuario(IdentificarUsuarioDto identificarUsuarioDto) {
        LOGGER.info(identificarUsuarioDto.toString());
        Usuario usuarioParametros = requestUsuarioMapper.requestDtoToDomain(identificarUsuarioDto);
        Usuario usuarioExistente = consultarUsuarioUseCase.buscarUsuarioPorLogin(usuarioParametros.getLogin(), false);
        if (usuarioExistente != null) {
            Boolean isSenhaValida = validarSenhaUsuarioUseCase.isSenhaValida(usuarioExistente.getSenha(), identificarUsuarioDto.getSenha());
            if (!isSenhaValida) {
                LOGGER.info("Senha inválida");
                throw new UsuarioValidacaoException("Senha incorreta informada para o login: " + identificarUsuarioDto.getLogin());
            }
            LOGGER.info("Usuario validado");
            return usuarioPresenter.toResponse(usuarioExistente);
        }
        Role role = consultarRoleUseCase.buscarRolePorNome("USUARIO");
        return usuarioPresenter.toResponse(salvarUsuarioUseCase.salvarUsuario(usuarioParametros, role));
    }
}
