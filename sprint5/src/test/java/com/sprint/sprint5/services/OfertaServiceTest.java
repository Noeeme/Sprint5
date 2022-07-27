package com.sprint.sprint5.services;

import com.sprint.sprint5.models.Item;
import com.sprint.sprint5.models.Oferta;
import com.sprint.sprint5.models.Pedido;
import org.apache.catalina.LifecycleState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class OfertaServiceTest {

    @InjectMocks
    private OfertaService ofertaService;

    @Test
//            (expected = DataValidadeOfertaInvalida.class)
    public void dataCriacaoDeOfertaMaiorQueValidade() {
        Pedido pedido = new Pedido();
        Item item = new Item();
        item.setId(1l);
        Oferta oferta = new Oferta();
        oferta.setId(1l);
        List<Oferta> ofertas = new ArrayList<>();
        List<Item> items = new ArrayList<>();

        LocalDateTime dateTime = LocalDateTime.of(2022,5,2,11,22,0);

        ofertas.add(oferta);
        item.setOfertas(ofertas);
        items.add(item);
        pedido.setItens(items);

        pedido.getItens().get(0).getOfertas().get(0).setDataCriacao(LocalDateTime.now());
        pedido.getItens().get(0).getOfertas().get(0).setDataValidade(dateTime);


        boolean data = ofertaService.dataCriacaoDeOfertaMaiorQueValidade(pedido);

        Assertions.assertTrue(data);
//        Assertions.assertNotNull(pedido);
//        Assertions.assertThrows("Data de validade da oferta inválida");
    }
}