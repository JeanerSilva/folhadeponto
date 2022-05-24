package com.ilia.ponto.folhadeponto.controller;

import javax.validation.Valid;

import com.ilia.ponto.folhadeponto.components.schemas.Mensagem;
import com.ilia.ponto.folhadeponto.components.schemas.Momento;
import com.ilia.ponto.folhadeponto.repository.Momentos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import static org.springframework.http.HttpStatus.*;

import java.util.List;

@RestController
@RequestMapping("/batidas")
@Api("Api Batidas")
public class batidasController {

    @Autowired
    private Momentos momentos;
    
    @PostMapping
    @ResponseStatus(CREATED)
    @ApiOperation("Salva um novo ponto")
    @ApiResponses({
            @ApiResponse(code = 201, message = Mensagem.CHECKIN),
            @ApiResponse(code = 400, message = Mensagem.DATA_FORMAT_ERROR),
            @ApiResponse(code = 403, message = Mensagem.MAX_REGISTRY),
            @ApiResponse(code = 403, message = Mensagem.LUNCH_MININAL_INTERVAL),
            @ApiResponse(code = 403, message = Mensagem.NO_WEEKEND_JOB),
            @ApiResponse(code = 409, message = Mensagem.DOUBLE_REGISTER)            
    })
    
    public Momento save( @RequestBody @Valid Momento momento ){
        return momentos.save(momento);
    }

    @GetMapping("/t")
    public boolean find( @RequestBody Momento filtro ){

        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher(
                                            ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        return momentos.findAll(example).size() <= 4;
   
    }


    @GetMapping("/t2")
    public List<Momento> lista(  ){
        return momentos.findByDataHora("2018-08-22");
    }

    @GetMapping("/t3")
    public List<Momento> lista3(  ){
        return momentos.findAll();
    }

}


        
