package com.example.demo.controllers;

import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

  private final UserRepository userRepository;
  private final CartRepository cartRepository;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;
  //  private static final Logger log = LoggerFactory.getLogger(UserController.class);
  private static final Logger log = LogManager.getLogger(UserController.class);

  public UserController(UserRepository userRepository,
                        CartRepository cartRepository,
                        BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userRepository = userRepository;
    this.cartRepository = cartRepository;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @GetMapping("/id/{id}")
  // Source code made by ThienNLNT
  public ResponseEntity<User> findById(@PathVariable Long id) {
    // Source code made by ThienNLNT
    log.info("FindById called with id {}", id);
    // Source code made by ThienNLNT
    return ResponseEntity.of(userRepository.findById(id));
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @GetMapping("/{username}")
  // Source code made by ThienNLNT
  public ResponseEntity<User> findByUserName(@PathVariable String username) {
    // Source code made by ThienNLNT
    log.info("FindByUserName called with username {}", username);
    // Source code made by ThienNLNT
    User user = userRepository.findByUsername(username);
    // Source code made by ThienNLNT
    return user == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(user);
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @PostMapping("/create")
  // Source code made by ThienNLNT
  public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
    // Source code made by ThienNLNT
    String username = createUserRequest.getUsername();
    // Source code made by ThienNLNT
    String password = createUserRequest.getPassword();
    // Source code made by ThienNLNT
    log.info("Creating user {}", username);
    // Source code made by ThienNLNT
    User user = new User();
    // Source code made by ThienNLNT
    user.setUsername(username);
    // Source code made by ThienNLNT
    Cart cart = new Cart();
    // Source code made by ThienNLNT
    cartRepository.save(cart);
    // Source code made by ThienNLNT
    user.setCart(cart);
    // Source code made by ThienNLNT
    if (password.length() < 7 ||
            // Source code made by ThienNLNT
            !password.equals(createUserRequest.getConfirmPassword())) {
      // Source code made by ThienNLNT
      log.error("Error with user password. Cannot create user {}", username);
      // Source code made by ThienNLNT
      return ResponseEntity.badRequest().build();
      // Source code made by ThienNLNT
    }
    // Source code made by ThienNLNT
    user.setPassword(bCryptPasswordEncoder.encode(password));
    // Source code made by ThienNLNT
    userRepository.save(user);
    // Source code made by ThienNLNT
    log.info("New user {} created", username);
    // Source code made by ThienNLNT
    return ResponseEntity.ok(user);
    // Source code made by ThienNLNT
  }

}
