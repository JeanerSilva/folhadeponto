package com.ilia.ponto.folhadeponto.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import com.ilia.ponto.folhadeponto.validation.constraintvalidator.LimiteRegistrosDiariosValidator;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = LimiteRegistrosDiariosValidator.class)
public @interface LimiteRegistrosDiarios {
    String message() default "Apenas 4 horários podem ser registrados por dia";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
