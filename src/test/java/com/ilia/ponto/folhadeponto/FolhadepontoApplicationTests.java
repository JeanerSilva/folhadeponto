package com.ilia.ponto.folhadeponto;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.validation.ConstraintViolationException;

import com.ilia.ponto.folhadeponto.components.schemas.Momento;
import com.ilia.ponto.folhadeponto.repository.Momentos;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FolhadepontoApplicationTests {


	@Autowired
    private Momentos momentosRepository;

	
    @Test
    public void testeVerificaHoraErrada () {
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            String dataHora = "2018-08-22T08:00:00s";
			Momento momentoSalvo = momentosRepository
                .save(new Momento(null, dataHora));
        });
    
        String expectedMessage = "Data e hora em formato inválido";
        String actualMessage = exception.getMessage();
    
        assertTrue(actualMessage.contains(expectedMessage));
    }

	@Test
    public void testeVerificaMomentoInexistente () {
        Exception exception = assertThrows(ConstraintViolationException.class, () -> {
            String dataHora = "";
			Momento momentoSalvo = momentosRepository
                .save(new Momento(null, dataHora));
        });
    
        String expectedMessage = "Campo obrigatório não informado";
        String actualMessage = exception.getMessage();
    
        assertTrue(actualMessage.contains(expectedMessage));
    }
}
