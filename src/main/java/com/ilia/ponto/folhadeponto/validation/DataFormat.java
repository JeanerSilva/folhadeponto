package com.ilia.ponto.folhadeponto.validation;

import com.ilia.ponto.folhadeponto.components.schemas.MensagemInterface;
import com.ilia.ponto.folhadeponto.validation.constraintvalidator.DataFormatValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DataFormatValidator.class)
public @interface DataFormat {
    String message() default MensagemInterface.DATA_FORMAT_ERROR;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
