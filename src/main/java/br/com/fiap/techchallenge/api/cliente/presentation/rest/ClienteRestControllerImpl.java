package br.com.fiap.techchallenge.api.cliente.presentation.rest;

import br.com.fiap.techchallenge.api.cliente.application.controller.ClienteController;
import br.com.fiap.techchallenge.api.cliente.common.domain.dto.request.ClienteRequestDto;
import br.com.fiap.techchallenge.api.cliente.common.domain.dto.request.IdentificarClienteDto;
import br.com.fiap.techchallenge.api.cliente.common.domain.dto.response.ClienteResponseDto;
import br.com.fiap.techchallenge.api.cliente.presentation.rest.interfaces.ClienteRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteRestControllerImpl implements ClienteRestController {
    private final ClienteController clienteController;

    @Override
    @GetMapping("/cpf")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDto buscarClientePorCpf(@RequestHeader("CPF") String cpf) {
        return clienteController.buscarClientePorCpf(cpf);
    }

    @Override
    @GetMapping("/email")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDto buscarClientePorEmail(@RequestHeader("EMAIL") String email) {
        return clienteController.buscarClientePorEmail(email);
    }

    @Override
    @GetMapping("/id")
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDto buscarClientePorId(@RequestParam UUID id) {
        return clienteController.buscarClientePorId(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ClienteResponseDto cadastrarCliente(@RequestBody ClienteRequestDto clienteRequestDto) {
        return clienteController.cadastrarCliente(clienteRequestDto);
    }

    @Override
    @PostMapping("/identificacao")
    @ResponseStatus(HttpStatus.OK)
    public  ClienteResponseDto identificarCliente(@RequestBody IdentificarClienteDto identificarClienteDto) {
        return clienteController.identificarCliente(identificarClienteDto);
    }

}
