package com.sprint.sprint5.services;

import com.sprint.sprint5.models.Item;
import com.sprint.sprint5.models.Oferta;
import com.sprint.sprint5.models.Pedido;
import com.sprint.sprint5.repositories.ItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @InjectMocks
    private ItemService itemService;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private ItemRepository itemRepository;

    @Test
    public void atualizarValorItem_sucesso() {


        Item item = new Item();
        item.setId(1l);
        item.setNome("arroz");
        item.setValor(12.0);

        Mockito.when(itemRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(item));

        itemService.atualizarValorItem(8.0, 1l);
        Assertions.assertNotNull(item);
        Mockito.verify(itemRepository).save(item);
        Assertions.assertEquals(8.0, item.getValor());
    }

    @Test
    public void dataValidadeOfertaVencida_sucesso(){
        Pedido pedido = new Pedido();
        Item item = new Item();
        Oferta oferta = new Oferta();
        List<Oferta> ofertas = new ArrayList<>();
        List<Item> items = new ArrayList<>();

        LocalDateTime dateTime = LocalDateTime.of(2022,5,2,11,22,0);

        oferta.setId(1l);
        oferta.setDataValidade(dateTime);
        ofertas.add(oferta);

        item.setOfertas(ofertas);
        items.add(item);
        pedido.setItens(items);

        boolean data = itemService.dataValidadeOfertaVencida(pedido);

        Assertions.assertFalse(data);
    }
}