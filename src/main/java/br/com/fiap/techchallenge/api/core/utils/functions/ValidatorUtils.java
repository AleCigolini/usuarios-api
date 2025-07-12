package br.com.fiap.techchallenge.api.core.utils.functions;

public class ValidatorUtils {
    public ValidatorUtils() {}

    public static Boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
