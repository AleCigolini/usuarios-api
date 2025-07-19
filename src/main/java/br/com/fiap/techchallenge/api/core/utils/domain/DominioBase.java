package br.com.fiap.techchallenge.api.core.utils.domain;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class DominioBase {

    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataAtualizacao;
}
