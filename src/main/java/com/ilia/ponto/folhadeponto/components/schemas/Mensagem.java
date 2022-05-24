package com.ilia.ponto.folhadeponto.components.schemas;

public enum Mensagem {

	DATA_FORMAT_ERROR("Data e hora em formato inválido"),
    NO_DEFAUL_FIELD("Campo obrigatório não informado"),
    MAX_REGISTRY("Apenas 4 horários podem ser registrados por dia"),
    LUNCH_MININAL_INTERVAL("Deve haver no mínimo 1 hora de almoço"),
    NO_WEEKEND_JOB("Sábado e domingo não são permitidos como dia de trabalho"),
    DOUBLE_REGISTER("Horários já registrado");

    private String descricao;

    Mensagem(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

}