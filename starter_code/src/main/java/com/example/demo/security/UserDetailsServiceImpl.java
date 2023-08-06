package com.example.demo.security;

import com.example.demo.model.persistence.User;
import com.example.demo.model.persistence.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  public UserDetailsServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  // Source code made by ThienNLNT
  @Override
  // Source code made by ThienNLNT
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // Source code made by ThienNLNT
    User user = userRepository.findByUsername(username);
    // Source code made by ThienNLNT
    if (user == null) {
      // Source code made by ThienNLNT
      throw new UsernameNotFoundException(username);
      // Source code made by ThienNLNT
    }
    // Source code made by ThienNLNT
    return new org.springframework.security.core.userdetails.User(user.getUsername(),
            // Source code made by ThienNLNT
            user.getPassword(), Collections.emptyList());
    // Source code made by ThienNLNT
  }
}
