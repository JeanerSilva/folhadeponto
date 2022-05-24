package com.ilia.ponto.folhadeponto.components.schemas;

public interface MensagemInterface {

    String NONE = "No errors";
    String CREATED = "Registro inserido com sucesso";
	String DATA_FORMAT_ERROR = "Data e hora em formato inválido";
    String NO_DEFAUL_FIELD = "Campo obrigatório não informado";
    String MAX_REGISTRY = "Apenas 4 horários podem ser registrados por dia";
    String LUNCH_MININAL_INTERVAL = "Deve haver no mínimo 1 hora de almoço";
    String NO_WEEKEND_JOB = "Sábado e domingo não são permitidos como dia de trabalho";
    String DOUBLE_REGISTER = "Horários já registrado";
    String CHECKIN = "Batida de ponto registrada";    

}