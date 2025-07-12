package br.com.fiap.techchallenge.api.core.utils.converter.email;

import br.com.fiap.techchallenge.api.core.utils.domain.Email;
import org.modelmapper.Converter;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

@Component
public class StringToEmailConverter implements Converter<String, Email> {
    @Override
    public Email convert(MappingContext<String, Email> context) {
        final var source = context.getSource();
        return source != null ? new Email(source) : null;
    }
}
