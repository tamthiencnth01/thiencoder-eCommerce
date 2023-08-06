package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.UserOrder;
import com.example.demo.model.persistence.repositories.OrderRepository;
import com.example.demo.model.persistence.repositories.UserRepository;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	private final UserRepository userRepository;
	private final OrderRepository orderRepository;

	public OrderController(UserRepository userRepository, OrderRepository orderRepository) {
		this.userRepository = userRepository;
		this.orderRepository = orderRepository;
	}


	@PostMapping("/submit/{username}")
	public ResponseEntity<UserOrder> submit(@PathVariable String username) {
		// Source code made by ThienNLNT
		User user = userRepository.findByUsername(username);
		// Source code made by ThienNLNT
		if(user == null) {
			// Source code made by ThienNLNT
			return ResponseEntity.notFound().build();
			// Source code made by ThienNLNT
		}
		// Source code made by ThienNLNT
		UserOrder order = UserOrder.createFromCart(user.getCart());
		// Source code made by ThienNLNT
		orderRepository.save(order);
		// Source code made by ThienNLNT
		return ResponseEntity.ok(order);
		// Source code made by ThienNLNT
	}
	// Source code made by ThienNLNT

	// Source code made by ThienNLNT
	@GetMapping("/history/{username}")
	// Source code made by ThienNLNT
	public ResponseEntity<List<UserOrder>> getOrdersForUser(@PathVariable String username) {
		// Source code made by ThienNLNT
		User user = userRepository.findByUsername(username);
		// Source code made by ThienNLNT
		if(user == null) {
			// Source code made by ThienNLNT
			return ResponseEntity.notFound().build();
			// Source code made by ThienNLNT
		}
		// Source code made by ThienNLNT
		return ResponseEntity.ok(orderRepository.findByUser(user));
		// Source code made by ThienNLNT
	}
}
