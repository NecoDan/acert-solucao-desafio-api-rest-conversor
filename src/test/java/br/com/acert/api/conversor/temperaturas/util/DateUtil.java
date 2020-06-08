package br.com.acert.api.conversor.temperaturas.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateUtil {

    private static final String MENSAGEM_VALIDACAO = "Parametro data encontra-se inválida e/ou inexistente.";

    public static LocalDate toLocalDateFromString(String value) {
        if (Objects.nonNull(value)) {
            DateTimeFormatter formatter = null;

            if (value.contains("-")) {
                formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            } else if (value.contains("/")) {
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            } else {
                formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            }
            return LocalDate.parse(value, formatter);
        }

        throw new IllegalArgumentException(MENSAGEM_VALIDACAO);
    }

    public static String toStringLocalDateFormatada(LocalDate data) {
        if (Objects.isNull(data))
            throw new IllegalArgumentException(MENSAGEM_VALIDACAO);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatter).toString();
    }
}
