package com.sprint.sprint5.controllers;

import com.sprint.sprint5.models.Pedido;
import com.sprint.sprint5.services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PatchMapping("/{id}/{valor}")
    public ResponseEntity<Pedido> atualizarValorItem(
            @PathVariable(value = "id") Long id,
            @PathVariable double valor){
        itemService.atualizarValorItem(valor, id);
        return ResponseEntity.noContent().build();
    }
}
