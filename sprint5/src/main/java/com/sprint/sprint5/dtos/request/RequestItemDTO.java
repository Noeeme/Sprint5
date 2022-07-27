package com.sprint.sprint5.dtos.request;

import com.sprint.sprint5.models.Oferta;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class RequestItemDTO {

    @NotBlank
    private String nome;
    private LocalDateTime dataCriação;
    private LocalDateTime dataValidade;
    @NotNull
    private double valor;
    @NotBlank
    private String descrição;
    private List<Oferta> ofertas;
}
