package com.ilia.ponto.folhadeponto.controller;

import javax.validation.Valid;
import com.ilia.ponto.folhadeponto.components.schemas.Mensagem;
import com.ilia.ponto.folhadeponto.components.schemas.MensagemInterface;
import com.ilia.ponto.folhadeponto.components.schemas.Momento;
import com.ilia.ponto.folhadeponto.repository.Momentos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

@RestController
@RequestMapping("/batidas")
@Api("Api Batidas")
public class BatidasController {

    @Autowired
    private Momentos momentos;

    @PostMapping
    @ApiOperation("Salva um novo ponto")
    @ApiResponses({
            @ApiResponse(code = 201, message = MensagemInterface.CHECKIN),
            @ApiResponse(code = 400, message = MensagemInterface.DATA_FORMAT_ERROR),
            @ApiResponse(code = 403, message = MensagemInterface.MAX_REGISTRY),
            @ApiResponse(code = 403, message = MensagemInterface.LUNCH_MININAL_INTERVAL),
            @ApiResponse(code = 403, message = MensagemInterface.NO_WEEKEND_JOB),
            @ApiResponse(code = 409, message = MensagemInterface.DOUBLE_REGISTER)
    })
    public ResponseEntity<Mensagem> save(@RequestBody @Valid Momento momento) {
        System.out.println("Momento: ============ " + momento);
        boolean allFieldsInformed = 
            verifyMandadotyFieldPresent(momento.getDataHora());
        
        Momento momentoSalvo = new Momento();
        Mensagem mensagem = new Mensagem();
        boolean entrySaved = false;
        boolean isNotThereFourEntriesSameDay = verifyMaxEntries(4, momento.getDataHora());
        boolean thereIsAtLeastOneLunchTime = verifyLunchTime();
        boolean itIsNotWeekend = verifyItIsNotWeekend(momento.getDataHora());
        boolean correctDataFormat = verifiyDataFormatIsOk(momento.getDataHora());
        
        boolean horarioAindaNaoRegistrado = verifyHoarioJaRegistrado(momento.getDataHora());

        boolean entryCanBeSaved = isNotThereFourEntriesSameDay
                && thereIsAtLeastOneLunchTime
                && itIsNotWeekend
                && correctDataFormat
                && allFieldsInformed
                && horarioAindaNaoRegistrado;

        HttpStatus statusCode ;

        if (entryCanBeSaved) {
            momentoSalvo = momentos.save(momento);
            if (momentoSalvo.getId() > 0) {
                entrySaved = true;
            }

            statusCode = HttpStatus.CREATED;
        } else {
            mensagem.setMensagem("No error message.");
            mensagem.setMensagem(isNotThereFourEntriesSameDay ? mensagem.getMensagem() : Mensagem.MAX_REGISTRY);
            mensagem.setMensagem(thereIsAtLeastOneLunchTime ? mensagem.getMensagem() : Mensagem.LUNCH_MININAL_INTERVAL);
            mensagem.setMensagem(itIsNotWeekend ? mensagem.getMensagem() : Mensagem.NO_WEEKEND_JOB);
            mensagem.setMensagem(correctDataFormat ? mensagem.getMensagem() : Mensagem.DATA_FORMAT_ERROR);
            mensagem.setMensagem(allFieldsInformed ? mensagem.getMensagem() : Mensagem.NO_DEFAUL_FIELD);
            statusCode = !horarioAindaNaoRegistrado ? HttpStatus.CONFLICT : HttpStatus.FORBIDDEN;
            mensagem.setMensagem(horarioAindaNaoRegistrado ? mensagem.getMensagem() : Mensagem.DOUBLE_REGISTER);
        }

        return entrySaved ? new ResponseEntity(statusCode)
                : new ResponseEntity<Mensagem>(mensagem, statusCode);
    }

    private boolean verifyHoarioJaRegistrado(String dataHora) {
        return numEntriesSameDay(dataHora).size() == 0;
    }

    private boolean verifiyDataFormatIsOk(String field) {
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

    private boolean verifyLunchTime() {
        return true;
    }

    private boolean verifyMandadotyFieldPresent(String dataHora) {
        System.out.println("dataHora ===========:" + dataHora);
        return dataHora != null && !dataHora.isEmpty();
    }

    private boolean verifyItIsNotWeekend(String dataHora) {
        String dateFormat = "uuuu-MM-dd'T'HH:mm:ss";

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter
                .ofPattern(dateFormat)
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDate date = LocalDate.parse(dataHora, dateTimeFormatter);
            DayOfWeek dayOfWeek = DayOfWeek.from(date);

            return dayOfWeek == DayOfWeek.valueOf("SATURDAY") ||
                    dayOfWeek == DayOfWeek.valueOf("SUNDAY") ? false : true;

        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private boolean verifyMaxEntries(Integer entry, String dataHora) {
        String dataString = dataHora.substring(0, 10);
        return numEntriesSameDay(dataString).size() < entry;
    }

    private List<Momento> numEntriesSameDay(String dataHora) {
        return momentos.findByDataHora(dataHora);
    }


}
