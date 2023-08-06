package com.example.demo.controllers;

import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {

  private final ItemRepository itemRepository;

  public ItemController(ItemRepository itemRepository) {
    this.itemRepository = itemRepository;
  }
  @GetMapping
  // Source code made by ThienNLNT
  public ResponseEntity<List<Item>> getItems() {
    // Source code made by ThienNLNT
    return ResponseEntity.ok(itemRepository.findAll());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @GetMapping("/{id}")
  // Source code made by ThienNLNT
  public ResponseEntity<Item> getItemById(@PathVariable Long id) {
    // Source code made by ThienNLNT
    return ResponseEntity.of(itemRepository.findById(id));
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @GetMapping("/name/{name}")
  // Source code made by ThienNLNT
  public ResponseEntity<List<Item>> getItemsByName(@PathVariable String name) {
    // Source code made by ThienNLNT
    List<Item> items = itemRepository.findByName(name);
    // Source code made by ThienNLNT
    return items == null || items.isEmpty() ? ResponseEntity.notFound().build()
            // Source code made by ThienNLNT
            : ResponseEntity.ok(items);
    // Source code made by ThienNLNT

    // Source code made by ThienNLNT
  }

}
