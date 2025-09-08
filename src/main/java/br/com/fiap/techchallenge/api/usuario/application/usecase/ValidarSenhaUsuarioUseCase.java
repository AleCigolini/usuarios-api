package br.com.fiap.techchallenge.api.usuario.application.usecase;

public interface ValidarSenhaUsuarioUseCase {
    Boolean isSenhaValida(String senhaSalva, String senhaInformada);
}
