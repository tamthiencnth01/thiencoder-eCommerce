package com.example.demo.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import java.util.Objects;

public class CartControllerTest {
  UserRepository userRepository;
  CartRepository cartRepository;
  ItemRepository itemRepository;
  CartController cartController;

  // Source code made by ThienNLNT
  @Before
  // Source code made by ThienNLNT
  public void setUp() {
    // Source code made by ThienNLNT
    userRepository = mock(UserRepository.class);
    // Source code made by ThienNLNT
    cartRepository = mock(CartRepository.class);
    // Source code made by ThienNLNT
    itemRepository = mock(ItemRepository.class);
    // Source code made by ThienNLNT
    cartController = new CartController(userRepository, cartRepository, itemRepository);
    // Source code made by ThienNLNT
    User user = new User();
    // Source code made by ThienNLNT
    user.setId(1);
    // Source code made by ThienNLNT
    user.setUsername("ThienNLNTDirector");
    // Source code made by ThienNLNT
    user.setPassword("admin");
    // Source code made by ThienNLNT
    user.setCart(new Cart());
    // Source code made by ThienNLNT
    when(userRepository.findByUsername("ThienNLNTDirector")).thenReturn(user);
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
    when(itemRepository.findById(1L)).thenReturn(java.util.Optional.of(item));
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void addCartOk() {
    // Source code made by ThienNLNT
    ModifyCartRequest request = new ModifyCartRequest();
    // Source code made by ThienNLNT
    request.setItemId(1);
    // Source code made by ThienNLNT
    request.setQuantity(1);
    // Source code made by ThienNLNT
    request.setUsername("ThienNLNTDirector");
    // Source code made by ThienNLNT
    ResponseEntity<Cart> response = cartController.addTocart(request);
    // Source code made by ThienNLNT
    assertNotNull(response);
    // Source code made by ThienNLNT
    assertEquals(200, response.getStatusCodeValue());
    // Source code made by ThienNLNT
    assertEquals(BigDecimal.valueOf(3.0), Objects.requireNonNull(response.getBody()).getTotal());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void addCartNotFoundUsername() {
    // Source code made by ThienNLNT
    ModifyCartRequest request = new ModifyCartRequest();
    // Source code made by ThienNLNT
    request.setItemId(1);
    // Source code made by ThienNLNT
    request.setQuantity(1);
    // Source code made by ThienNLNT
    request.setUsername("abc");
    // Source code made by ThienNLNT
    ResponseEntity<Cart> response = cartController.addTocart(request);
    // Source code made by ThienNLNT
    assertEquals(404, response.getStatusCodeValue());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void addCartNotFoundItemId() {
    // Source code made by ThienNLNT
    ModifyCartRequest request = new ModifyCartRequest();
    // Source code made by ThienNLNT
    request.setItemId(2);
    // Source code made by ThienNLNT
    request.setQuantity(1);
    // Source code made by ThienNLNT
    request.setUsername("ThienNLNTDirector");
    // Source code made by ThienNLNT
    ResponseEntity<Cart> response = cartController.addTocart(request);
    // Source code made by ThienNLNT
    assertEquals(404, response.getStatusCodeValue());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void removeFromCart() {
    // Source code made by ThienNLNT
    ModifyCartRequest request = new ModifyCartRequest();
    // Source code made by ThienNLNT
    request.setItemId(1);
    // Source code made by ThienNLNT
    request.setQuantity(3);
    // Source code made by ThienNLNT
    request.setUsername("ThienNLNTDirector");
    // Source code made by ThienNLNT
    cartController.addTocart(request);
    // Source code made by ThienNLNT
    request = new ModifyCartRequest();
    // Source code made by ThienNLNT
    request.setItemId(1);
    // Source code made by ThienNLNT
    request.setQuantity(1);
    // Source code made by ThienNLNT
    request.setUsername("ThienNLNTDirector");
    // Source code made by ThienNLNT
    ResponseEntity<Cart> response = cartController.removeFromcart(request);
    // Source code made by ThienNLNT
    assertEquals(200, response.getStatusCodeValue());
    // Source code made by ThienNLNT
    assertEquals(BigDecimal.valueOf(6.0), Objects.requireNonNull(response.getBody()).getTotal());
    // Source code made by ThienNLNT

    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void removeFromCartNotFoundUser() {
    // Source code made by ThienNLNT
    ModifyCartRequest request = new ModifyCartRequest();
    // Source code made by ThienNLNT
    request.setItemId(1);
    // Source code made by ThienNLNT
    request.setQuantity(1);
    // Source code made by ThienNLNT
    request.setUsername("abc");
    // Source code made by ThienNLNT
    ResponseEntity<Cart> response = cartController.removeFromcart(request);
    // Source code made by ThienNLNT
    assertEquals(404, response.getStatusCodeValue());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void removeFromCartNotFoundItemId() {
    // Source code made by ThienNLNT
    ModifyCartRequest request = new ModifyCartRequest();
    // Source code made by ThienNLNT
    request.setItemId(2);
    // Source code made by ThienNLNT
    request.setQuantity(1);
    // Source code made by ThienNLNT
    request.setUsername("ThienNLNTDirector");
    // Source code made by ThienNLNT
    ResponseEntity<Cart> response = cartController.removeFromcart(request);
    // Source code made by ThienNLNT
    assertEquals(404, response.getStatusCodeValue());
    // Source code made by ThienNLNT
  }
}