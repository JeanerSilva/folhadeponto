package com.ilia.ponto.folhadeponto.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


public class MomentoValidation {
    

    private boolean verifiyDataFormatIsOk(String field) {
        System.out.println("======== field: " + field);
        if (field != null && !field.isEmpty()) {
            String dateFormat = "uuuu-MM-dd'T'HH:mm:ss";

            DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                    .ofPattern(dateFormat)
                    .withResolverStyle(ResolverStyle.STRICT);
            try {
                LocalDate date = LocalDate.parse(field, dateTimeFormatter);
                return true;

            } catch (DateTimeParseException e) {
                return false;
            }
        } else {
            return true;
        }
    }

    private boolean verifyLunchTime() {
        return true;
    }

    private boolean verifyMandadotyFieldPresent(String dataHora) {
        return dataHora != null && !dataHora.isEmpty();
    }

    private boolean verifyItIsNotWeekend() {
        return true;
    }
}
