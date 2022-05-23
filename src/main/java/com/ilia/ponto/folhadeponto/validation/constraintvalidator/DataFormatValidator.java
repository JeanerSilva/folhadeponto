package com.ilia.ponto.folhadeponto.validation.constraintvalidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ilia.ponto.folhadeponto.validation.DataFormat;

public class DataFormatValidator
        implements ConstraintValidator<DataFormat, String> {

    @Override
    public boolean isValid(String field,
            ConstraintValidatorContext constraintValidatorContext) {
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

    @Override
    public void initialize(DataFormat constraintAnnotation) {
    }
}