package com.ilia.ponto.folhadeponto.validation.constraintvalidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.ilia.ponto.folhadeponto.validation.NotEmptyField;

public class NotEmptyValidator
        implements ConstraintValidator<NotEmptyField, String> {

    @Override
    public boolean isValid(String field,
                           ConstraintValidatorContext constraintValidatorContext) {
           return field != null && !field.isEmpty();
    }

    @Override
    public void initialize( NotEmptyField constraintAnnotation ) {
    }
}