package br.com.fiap.techchallenge03.core.utils.converter.cpf;

import br.com.fiap.techchallenge03.core.utils.domain.Cpf;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class StringToCpfConverter implements Converter<String, Cpf> {
    @Override
    public Cpf convert(MappingContext<String, Cpf> context) {
        final var source = context.getSource();
        return source != null ? new Cpf(source) : null;
    }
}
