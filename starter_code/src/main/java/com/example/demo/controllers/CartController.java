package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.Item;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.ItemRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.ModifyCartRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/cart")
public class CartController {

  private final UserRepository userRepository;
  private final CartRepository cartRepository;
  private final ItemRepository itemRepository;

  public CartController(UserRepository userRepository,
                        CartRepository cartRepository,
                        ItemRepository itemRepository) {
    this.userRepository = userRepository;
    this.cartRepository = cartRepository;
    this.itemRepository = itemRepository;
  }
  @PostMapping("/addToCart")
  // Source code made by ThienNLNT
  public ResponseEntity<Cart> addTocart(@RequestBody ModifyCartRequest request) {
    // Source code made by ThienNLNT
    User user = userRepository.findByUsername(request.getUsername());
    // Source code made by ThienNLNT
    if (user == null) {
      // Source code made by ThienNLNT
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      // Source code made by ThienNLNT
    }
    // Source code made by ThienNLNT
    Optional<Item> item = itemRepository.findById(request.getItemId());
    // Source code made by ThienNLNT
    if (!item.isPresent()) {
      // Source code made by ThienNLNT
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      // Source code made by ThienNLNT
    }
    // Source code made by ThienNLNT
    Cart cart = user.getCart();
    // Source code made by ThienNLNT
    IntStream.range(0, request.getQuantity())
            // Source code made by ThienNLNT
            .forEach(i -> cart.addItem(item.get()));
    // Source code made by ThienNLNT
    cartRepository.save(cart);
    // Source code made by ThienNLNT
    return ResponseEntity.ok(cart);
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @PostMapping("/removeFromCart")
  // Source code made by ThienNLNT
  public ResponseEntity<Cart> removeFromcart(@RequestBody ModifyCartRequest request) {
    // Source code made by ThienNLNT
    User user = userRepository.findByUsername(request.getUsername());
    // Source code made by ThienNLNT
    if (user == null) {
      // Source code made by ThienNLNT
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      // Source code made by ThienNLNT
    }
    // Source code made by ThienNLNT
    Optional<Item> item = itemRepository.findById(request.getItemId());
    // Source code made by ThienNLNT
    if (!item.isPresent()) {
      // Source code made by ThienNLNT
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
      // Source code made by ThienNLNT
    }
    // Source code made by ThienNLNT
    Cart cart = user.getCart();
    // Source code made by ThienNLNT
    IntStream.range(0, request.getQuantity())
            // Source code made by ThienNLNT
            .forEach(i -> cart.removeItem(item.get()));
    // Source code made by ThienNLNT
    cartRepository.save(cart);
    // Source code made by ThienNLNT
    return ResponseEntity.ok(cart);
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

}
