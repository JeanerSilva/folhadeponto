package com.ilia.ponto.folhadeponto.validation.constraintvalidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.ilia.ponto.folhadeponto.repository.Momentos;
import com.ilia.ponto.folhadeponto.validation.LimiteRegistrosDiarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;


public class LimiteRegistrosDiariosValidator
        implements ConstraintValidator<LimiteRegistrosDiarios, String> {

    @Autowired
    private Momentos momentosRepository;

    @Override
    public boolean isValid(String field,
                           ConstraintValidatorContext constraintValidatorContext) {
                            ExampleMatcher matcher = ExampleMatcher
                            .matching()
                            .withIgnoreCase()
                            .withStringMatcher(
                                    ExampleMatcher.StringMatcher.CONTAINING );

            Example example = Example.of(field, matcher);
            return momentosRepository.findAll(example).size() >= 4;
                           }
            

    @Override
    public void initialize( LimiteRegistrosDiarios constraintAnnotation ) {
    }
}