package com.sprint.sprint5.controllers;

import com.sprint.sprint5.dtos.request.RequestItemDTO;
import com.sprint.sprint5.dtos.request.RequestPedidoDTO;
import com.sprint.sprint5.dtos.response.ResponsePedidoDTO;
import com.sprint.sprint5.models.Pedido;
import com.sprint.sprint5.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/pedido")
public class PedidoController {

    final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<ResponsePedidoDTO> mostrarPorId(@PathVariable Long id){
        ResponsePedidoDTO responsePedidoDTO = pedidoService.mostrarId(id);
        return ResponseEntity.ok(responsePedidoDTO);
    }

    @GetMapping
    public ResponseEntity<List<ResponsePedidoDTO>> mostrarTodos(@RequestParam(required = false) String cpf,
                                                                @RequestParam(required = false, defaultValue = "total") String sortAscBy){
        List<ResponsePedidoDTO> responsePedidoDTOS = pedidoService.mostrarTodos(cpf, sortAscBy);
        return ResponseEntity.ok(responsePedidoDTOS);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<ResponsePedidoDTO> salvar(@RequestBody @Valid RequestPedidoDTO request){
        ResponsePedidoDTO responsePedidoDTO = pedidoService.salvar(request);
        return  ResponseEntity.ok(responsePedidoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        pedidoService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/{total}")
    public ResponseEntity<Pedido> atualizarTotalPedido(
            @PathVariable(value = "id") Long id,
            @PathVariable double total){
        pedidoService.atualizarTotalPedido(total, id);
        return ResponseEntity.noContent().build();
    }
}
