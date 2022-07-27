package com.sprint.sprint5.dtos.response;

import com.sprint.sprint5.models.Item;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class ResponsePedidoDTO {

    private  Long id;
    private String cpf;
    private List<Item> itens;
    private double total;

}
