package com.sprint.sprint5.services;

import com.sprint.sprint5.models.Oferta;
import com.sprint.sprint5.models.Pedido;
import net.minidev.json.JSONObject;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

@Service
public class OfertaService {


    public boolean dataCriacaoDeOfertaMaiorQueValidade(Pedido pedido){
        boolean valido = true;
        for(int i = 0; i<pedido.getItens().size(); i++) {
            for (int j = 0; j < pedido.getItens().get(i).getOfertas().size(); j++) {
                if (pedido.getItens().get(i).getOfertas().get(j).getDataValidade() != null){
                    valido = pedido.getItens().get(i).getOfertas().get(j).getDataCriacao()
                            .isAfter(pedido.getItens().get(i).getOfertas().get(j).getDataValidade());
                }
            }
        }
        return valido;
    }
}
