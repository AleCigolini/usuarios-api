package br.com.fiap.techchallenge03.core.utils.functions;

public class ValidatorUtils {
    public ValidatorUtils() {}

    public static Boolean isNotBlank(String value) {
        return value != null && !value.trim().isEmpty();
    }
}
