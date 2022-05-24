package com.ilia.ponto.folhadeponto.validation.constraintvalidator;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.ilia.ponto.folhadeponto.components.schemas.Momento;
import com.ilia.ponto.folhadeponto.repository.Momentos;
import com.ilia.ponto.folhadeponto.validation.LimiteRegistrosDiarios;
import org.springframework.beans.factory.annotation.Autowired;

public class LimiteRegistrosDiariosValidator
        implements ConstraintValidator<LimiteRegistrosDiarios, String> {

    @Autowired
    private Momentos momentosRepository;

    @Override
    public boolean isValid(String field,
            ConstraintValidatorContext constraintValidatorContext) {
        String dataString = field.substring(0, 10);

            List<Momento> momentos = momentosRepository.findAll();

            
        return momentos.stream().filter( f -> f.getDataHora().startsWith(dataString)).collect(Collectors.toList())
                .size() <= 4;
    }

    @Override
    public void initialize(LimiteRegistrosDiarios constraintAnnotation) {
    }
}