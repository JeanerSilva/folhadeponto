package com.ilia.ponto.folhadeponto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.ilia.ponto.folhadeponto.validation.constraintvalidator.NotEmptyValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyValidator.class)
public @interface NotEmptyField {
    String message() default "Campo obrigatório não informado.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
