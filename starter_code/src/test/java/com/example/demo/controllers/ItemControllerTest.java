package com.example.demo.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.repositories.ItemRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ItemControllerTest {
  ItemRepository itemRepository;
  ItemController itemController;

  // Source code made by ThienNLNT
  @Before
  // Source code made by ThienNLNT
  public void setUp() {
    // Source code made by ThienNLNT
    itemRepository = mock(ItemRepository.class);
    // Source code made by ThienNLNT
    itemController = new ItemController(itemRepository);
    // Source code made by ThienNLNT
    Item item = new Item();
    // Source code made by ThienNLNT
    item.setId(1L);
    // Source code made by ThienNLNT
    item.setName("Item 1");
    // Source code made by ThienNLNT
    item.setPrice(BigDecimal.valueOf(3.0));
    // Source code made by ThienNLNT
    item.setDescription("This is Item 1");
    // Source code made by ThienNLNT
    when(itemRepository.findAll()).thenReturn(Collections.singletonList(item));
    // Source code made by ThienNLNT
    when(itemRepository.findById(1L)).thenReturn(Optional.of(item));
    // Source code made by ThienNLNT
    when(itemRepository.findByName("Item 1")).thenReturn(Collections.singletonList(item));
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void getItems() {
    // Source code made by ThienNLNT
    ResponseEntity<List<Item>> response = itemController.getItems();
    // Source code made by ThienNLNT
    assertEquals(200, response.getStatusCodeValue());
    // Source code made by ThienNLNT
    assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void getItemById() {
    // Source code made by ThienNLNT
    ResponseEntity<Item> response = itemController.getItemById(1L);
    // Source code made by ThienNLNT
    assertEquals(200, response.getStatusCodeValue());
    // Source code made by ThienNLNT
    assertEquals("Item 1", Objects.requireNonNull(response.getBody()).getName());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void getItemsByName() {
    // Source code made by ThienNLNT
    ResponseEntity<List<Item>> response = itemController.getItemsByName("Item 1");
    // Source code made by ThienNLNT
    assertEquals(200, response.getStatusCodeValue());
    // Source code made by ThienNLNT
    assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void getItemsByNameNotFound() {
    // Source code made by ThienNLNT
    ResponseEntity<List<Item>> response = itemController.getItemsByName("abc");
    // Source code made by ThienNLNT
    assertEquals(404, response.getStatusCodeValue());
    // Source code made by ThienNLNT
  }
}