package com.sprint.sprint5.services;

import com.sprint.sprint5.dtos.request.RequestPedidoDTO;
import com.sprint.sprint5.dtos.response.ResponsePedidoDTO;
import com.sprint.sprint5.exceptions.oferta.DataDeValidadeDaOfertaInvalidaException;
import com.sprint.sprint5.models.Item;
import com.sprint.sprint5.models.Pedido;
import com.sprint.sprint5.repositories.PedidoRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private ItemService itemService;

    @Mock
    private OfertaService ofertaService;

    @Mock
    private PedidoRepository pedidoRepository;

    private Pedido pedido;
    private RequestPedidoDTO requestPedidoDTO;
    private ResponsePedidoDTO responsePedidoDTO;
    private List<Pedido> pedidos = new ArrayList<>();

    @Mock
    private ModelMapper modelMapper;

    @BeforeEach
    public void iniciar(){

        RequestPedidoDTO requestPedidoDTO = new RequestPedidoDTO();
        requestPedidoDTO.setCpf("513.155.955-44");
        requestPedidoDTO.setTotal(10.0);

        List<Pedido> pedidos = new ArrayList<>();

        Pedido pedido1 = new Pedido();
        pedido1.setId(1l);
        pedido1.setCpf("513.155.955-44");
        pedido1.setTotal(30.0);
        pedidos.add(pedido1);

        Pedido pedido2 = new Pedido();
        pedido2.setId(2l);
        pedido2.setCpf("513.155.965-44");
        pedido2.setTotal(50.0);
        pedidos.add(pedido2);

        this.pedido = pedido1;
        this.responsePedidoDTO = responsePedidoDTO;
        this.requestPedidoDTO = requestPedidoDTO;
        this.pedidos = pedidos;
    }

    @Test
    public void mostrarId() {
        when(pedidoRepository.findById(1l)).thenReturn(Optional.of(pedido));
        pedidoService.mostrarId(1l);
        Assertions.assertEquals(1l,pedido.getId());
    }

    @Test
    public void mostrarTodos() {
        when(pedidoRepository.findWithFilters("513.155.955-44", Sort.by("ASC"))).thenReturn(pedidos);
        List<ResponsePedidoDTO> responsePedidoDTOS = pedidoService.mostrarTodos("513.155.955-44", "ASC");

        Assertions.assertEquals(pedidos.size(), responsePedidoDTOS.size());
    }

    @Test
    public void salvar() {
        when(modelMapper.map(requestPedidoDTO, Pedido.class)).thenReturn(pedido);

        pedidoService.salvar(requestPedidoDTO);

        Mockito.verify(pedidoRepository).save(pedido);
        Assertions.assertEquals("513.155.955-44", requestPedidoDTO.getCpf());
    }

//    @Test
//    public void salvar_exception() {
//        String dataValida = "30/05/2022";
//
//        Assertions.assertThrows(DataDeValidadeDaOfertaInvalidaException.class, () -> this.modelMapper)
//    }

    @Test
    void deletar() {
        Pedido pedido = new Pedido();
        pedido.setId(1l);
        when(pedidoRepository.findById(1l)).thenReturn(Optional.of(pedido));

        pedidoService.deletar(1l);

        verify(pedidoRepository, times(1)).findById(1l);
    }

    @Test
    public void atualizarTotalPedido() {

        Pedido pedido = new Pedido();
        pedido.setId(1l);
        pedido.setTotal(8.0);
        pedido.setCpf("123.321.456-78");

        Mockito.when(pedidoRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(pedido));

        pedidoService.atualizarTotalPedido(10.0, 1l);
        Assertions.assertNotNull(pedido);
        Mockito.verify(pedidoRepository).save(pedido);
        Assertions.assertEquals(10.0, pedido.getTotal());
    }
}