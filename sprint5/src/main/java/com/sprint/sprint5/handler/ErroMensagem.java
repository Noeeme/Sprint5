package com.sprint.sprint5.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroMensagem {

    private String messagem;

    private List<String> validationsErrors;

    public ErroMensagem(String messagem) {
        this.messagem = messagem;
    }

    public ErroMensagem(List<String> validationsErrors) {
        this.validationsErrors = validationsErrors;
    }
}
