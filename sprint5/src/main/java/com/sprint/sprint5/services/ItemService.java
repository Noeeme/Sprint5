package com.sprint.sprint5.services;

import com.sprint.sprint5.models.Item;
import com.sprint.sprint5.models.Oferta;
import com.sprint.sprint5.models.Pedido;
import com.sprint.sprint5.repositories.ItemRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ModelMapper modelMapper;
    public void atualizarValorItem(double valor, Long id) {
        try {
            Item item = itemRepository.findById(id).get();
            item.setValor(valor);
//            modelMapper.map(item, Item.class);
            itemRepository.save(item);
        }catch(Exception e){
            return;
        }
    }

    public boolean dataValidadeOfertaVencida(Pedido pedido){

        boolean validado = true;
        for(int i = 0; i<pedido.getItens().size(); i++) {
            for (int j = 0; j < pedido.getItens().get(i).getOfertas().size(); j++) {
                if (pedido.getItens().get(i).getOfertas().get(j).getDataValidade() != null){
                    LocalDateTime dateTime = LocalDateTime.now();
                    validado = pedido.getItens().get(i).getOfertas().get(j).getDataValidade()
                            .isAfter(dateTime);
                }
            }
        }
        return validado;
    }
}
