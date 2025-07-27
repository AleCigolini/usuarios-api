package br.com.fiap.techchallenge.api.core.utils.validators.cpf.helper;

public class ValidadorCpf {
    private static String CPF_CLIENTE_PADRAO = "00000000000";

    public static boolean isValidCPF(String cpf) {
        if (cpf.length() != 11 || (cpf.matches("(\\d)\\1{10}") && !cpf.equals(CPF_CLIENTE_PADRAO))) {
            return(false);
        }
        return true;
    }

}
