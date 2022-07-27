package com.sprint.sprint5.dtos.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RequestOfertaDTO {

    @NotBlank
    private String nome;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataValidade;
    @NotNull
    private double desconto;
    @NotBlank
    private String descricao;
}
