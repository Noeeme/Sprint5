package com.sprint.sprint5.dtos.request;

import com.sprint.sprint5.models.Item;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.util.List;

@Data
public class RequestPedidoDTO {

    @CPF
    private String cpf;
    @NotNull
    private List<Item> itens;
    @NotNull
    private double total;
}
