package com.example.demo.controllers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderControllerTest {
  UserRepository userRepository;
  OrderRepository orderRepository;
  OrderController orderController;

  // Source code made by ThienNLNT
  @Before
  // Source code made by ThienNLNT
  public void setUp() {
    // Source code made by ThienNLNT
    userRepository = mock(UserRepository.class);
    // Source code made by ThienNLNT
    orderRepository = mock(OrderRepository.class);
    // Source code made by ThienNLNT
    orderController = new OrderController(userRepository, orderRepository);
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
    List<Item> items = new ArrayList<>();
    // Source code made by ThienNLNT
    items.add(item);
    // Source code made by ThienNLNT
    User user = new User();
    // Source code made by ThienNLNT
    Cart cart = new Cart();
    // Source code made by ThienNLNT
    user.setId(1);
    // Source code made by ThienNLNT
    user.setUsername("ThienNLNTDirector");
    // Source code made by ThienNLNT
    user.setPassword("admin");
    // Source code made by ThienNLNT
    cart.setId(1L);
    // Source code made by ThienNLNT
    cart.setUser(user);
    // Source code made by ThienNLNT
    cart.setItems(items);
    // Source code made by ThienNLNT
    cart.setTotal(BigDecimal.valueOf(3.0));
    // Source code made by ThienNLNT
    user.setCart(cart);
    // Source code made by ThienNLNT
    when(userRepository.findByUsername("ThienNLNTDirector")).thenReturn(user);
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void submit() {
    // Source code made by ThienNLNT
    ResponseEntity<UserOrder> response = orderController.submit("ThienNLNTDirector");
    // Source code made by ThienNLNT
    assertEquals(200, response.getStatusCodeValue());
    // Source code made by ThienNLNT
    assertEquals(1, Objects.requireNonNull(response.getBody()).getItems().size());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void submitNotFound() {
    // Source code made by ThienNLNT
    ResponseEntity<UserOrder> response = orderController.submit("abc");
    // Source code made by ThienNLNT
    assertEquals(404, response.getStatusCodeValue());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void getOrdersForUser() {
    // Source code made by ThienNLNT
    ResponseEntity<List<UserOrder>> orders = orderController.getOrdersForUser("ThienNLNTDirector");
    // Source code made by ThienNLNT
    assertEquals(200, orders.getStatusCodeValue());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void getOrdersForUserNotFound() {
    // Source code made by ThienNLNT
    ResponseEntity<List<UserOrder>> orders = orderController.getOrdersForUser("abc");
    // Source code made by ThienNLNT
    assertEquals(404, orders.getStatusCodeValue());
    // Source code made by ThienNLNT

    // Source code made by ThienNLNT
  }
}