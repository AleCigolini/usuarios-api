package br.com.fiap.techchallenge.api.core.utils.converter.email;

import br.com.fiap.techchallenge.api.core.utils.domain.Email;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class EmailToStringConverter implements Converter<Email, String> {
    @Override
    public String convert(MappingContext<Email, String> context) {
        final var source = context.getSource();
        return source != null ? source.getEndereco() : null;
    }
}
