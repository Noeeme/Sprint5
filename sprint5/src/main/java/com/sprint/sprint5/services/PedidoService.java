package com.sprint.sprint5.services;

import com.sprint.sprint5.dtos.request.RequestPedidoDTO;
import com.sprint.sprint5.dtos.response.ResponsePedidoDTO;
import com.sprint.sprint5.exceptions.oferta.DataDeValidadeDaOfertaInvalidaException;
import com.sprint.sprint5.exceptions.pedido.PedidoNotFound;
import com.sprint.sprint5.models.Pedido;
import com.sprint.sprint5.repositories.PedidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private OfertaService ofertaService;
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ModelMapper modelMapper;
    public ResponsePedidoDTO mostrarId(Long id){
        Pedido pedido = pedidoRepository.findById(id).orElseThrow(PedidoNotFound::new);
        return modelMapper.map(pedido, ResponsePedidoDTO.class);
    }
    public List<ResponsePedidoDTO> mostrarTodos(String cpf, String sortAscBy){
        List<Pedido> pedidos = pedidoRepository.findWithFilters(cpf, Sort.by(Sort.Direction.ASC, sortAscBy));
        List<ResponsePedidoDTO> dtos = pedidos.stream().map(pedido -> modelMapper.map(pedido,
                ResponsePedidoDTO.class)).collect(Collectors.toList());
        return dtos;
    }
    public ResponsePedidoDTO salvar(RequestPedidoDTO requestPedidoDTO){
        Pedido pedido = modelMapper.map(requestPedidoDTO, Pedido.class);
            if(itemService.dataValidadeOfertaVencida(pedido))
            {
               throw new DataDeValidadeDaOfertaInvalidaException();
            }
            if(ofertaService.dataCriacaoDeOfertaMaiorQueValidade(pedido)){
                throw new DataDeValidadeDaOfertaInvalidaException();
            }

        Pedido pedidoSalvo = pedidoRepository.save(pedido);
        return modelMapper.map(pedidoSalvo, ResponsePedidoDTO.class);
    }
    public void deletar(Long id){
        pedidoRepository.findById(id).orElseThrow(PedidoNotFound::new);
        pedidoRepository.deleteById(id);
    }


    public void atualizarTotalPedido(double total, Long id) {
        try {
            Pedido pedido = pedidoRepository.findById(id).orElseThrow(PedidoNotFound::new);
            pedido.setTotal(total);
//            modelMapper.map(pedido, Pedido.class);
            pedidoRepository.save(pedido);
        }catch(Exception e){
            return;
        }
    }

}
