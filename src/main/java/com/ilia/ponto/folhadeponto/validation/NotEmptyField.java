package com.ilia.ponto.folhadeponto.validation;

import com.ilia.ponto.folhadeponto.components.schemas.MensagemInterface;
import com.ilia.ponto.folhadeponto.validation.constraintvalidator.NotEmptyValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyValidator.class)
public @interface NotEmptyField {
    String message() default MensagemInterface.NO_DEFAUL_FIELD;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
