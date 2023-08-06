package com.example.demo.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import com.example.demo.model.persistence.Cart;
import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.CartRepository;
import com.example.demo.model.persistence.repositories.UserRepository;
import com.example.demo.model.requests.CreateUserRequest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Objects;
import java.util.Optional;

public class UserControllerTest {
  UserRepository userRepository;
  CartRepository cartRepository;
  BCryptPasswordEncoder bCryptPasswordEncoder;
  UserController userController;

  // Source code made by ThienNLNT
  @Before
  // Source code made by ThienNLNT
  public void setUp() {
    // Source code made by ThienNLNT
    userRepository = mock(UserRepository.class);
    // Source code made by ThienNLNT
    cartRepository = mock(CartRepository.class);
    // Source code made by ThienNLNT
    bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
    // Source code made by ThienNLNT
    userController = new UserController(userRepository, cartRepository, bCryptPasswordEncoder);
    // Source code made by ThienNLNT
    User user = new User();
    // Source code made by ThienNLNT
    user.setId(1);
    // Source code made by ThienNLNT
    user.setUsername("ThienNLNT");
    // Source code made by ThienNLNT
    user.setPassword("ThienNLNTDirector");
    // Source code made by ThienNLNT
    user.setCart(new Cart());
    // Source code made by ThienNLNT
    when(userRepository.findByUsername("ThienNLNT")).thenReturn(user);
    // Source code made by ThienNLNT
    when(userRepository.findById(1L)).thenReturn(Optional.of(user));
    // Source code made by ThienNLNT
    when(bCryptPasswordEncoder.encode("ThienNLNTDirector")).thenReturn("ThienNLNTDirectorEncode");
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void findById() {
    // Source code made by ThienNLNT
    ResponseEntity<User> response = userController.findById(1L);
    // Source code made by ThienNLNT
    assertEquals(200, response.getStatusCodeValue());
    // Source code made by ThienNLNT
    assertEquals(1, Objects.requireNonNull(response.getBody()).getId());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void findByIdNotFound() {
    // Source code made by ThienNLNT
    ResponseEntity<User> response = userController.findById(2L);
    // Source code made by ThienNLNT
    assertEquals(404, response.getStatusCodeValue());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void findByUserName() {
    // Source code made by ThienNLNT
    ResponseEntity<User> response = userController.findByUserName("ThienNLNT");
    // Source code made by ThienNLNT
    assertEquals(200, response.getStatusCodeValue());
    // Source code made by ThienNLNT
    assertEquals("ThienNLNT", Objects.requireNonNull(response.getBody()).getUsername());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void findByUserNameNotFound() {
    // Source code made by ThienNLNT
    ResponseEntity<User> response = userController.findByUserName("abc");
    // Source code made by ThienNLNT
    assertEquals(404, response.getStatusCodeValue());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void createUser() {
    // Source code made by ThienNLNT
    CreateUserRequest request = new CreateUserRequest();
    // Source code made by ThienNLNT
    request.setUsername("ThienNLNT");
    // Source code made by ThienNLNT
    request.setPassword("ThienNLNTDirector");
    // Source code made by ThienNLNT
    request.setConfirmPassword("ThienNLNTDirector");
    // Source code made by ThienNLNT
    ResponseEntity<User> response = userController.createUser(request);
    // Source code made by ThienNLNT
    assertEquals(200, response.getStatusCodeValue());
    // Source code made by ThienNLNT
    User user = response.getBody();
    // Source code made by ThienNLNT
    assertNotNull(user);
    // Source code made by ThienNLNT
    assertEquals(0, user.getId());
    // Source code made by ThienNLNT
    assertEquals("ThienNLNT", user.getUsername());
    // Source code made by ThienNLNT
    assertEquals("ThienNLNTDirectorEncode", user.getPassword());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void createUserBadRequest() {
    // Source code made by ThienNLNT
    CreateUserRequest request = new CreateUserRequest();
    // Source code made by ThienNLNT
    request.setUsername("ThienNLNT");
    // Source code made by ThienNLNT
    request.setPassword("admin");
    // Source code made by ThienNLNT
    request.setConfirmPassword("admin");
    // Source code made by ThienNLNT
    ResponseEntity<User> response = userController.createUser(request);
    // Source code made by ThienNLNT
    assertEquals(400, response.getStatusCodeValue());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Test
  // Source code made by ThienNLNT
  public void create_user_password_confirm_mismatch() {
    // Source code made by ThienNLNT
    CreateUserRequest request = new CreateUserRequest();
    // Source code made by ThienNLNT
    request.setUsername("ThienNLNT");
    // Source code made by ThienNLNT
    request.setPassword("admin");
    // Source code made by ThienNLNT
    request.setConfirmPassword("Admin");
    // Source code made by ThienNLNT
    ResponseEntity<User> response = userController.createUser(request);
    // Source code made by ThienNLNT
    assertEquals(400, response.getStatusCodeValue());
    // Source code made by ThienNLNT
  }
}