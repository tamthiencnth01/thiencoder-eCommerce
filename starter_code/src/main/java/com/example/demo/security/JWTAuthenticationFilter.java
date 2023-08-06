package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.example.demo.controllers.UserController;
import com.example.demo.model.persistence.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

  private final AuthenticationManager authenticationManager;
  private static final Logger log = LogManager.getLogger(JWTAuthenticationFilter.class);

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  // Source code made by ThienNLNT
  @Override
  // Source code made by ThienNLNT
  public Authentication attemptAuthentication(HttpServletRequest req,
                                              // Source code made by ThienNLNT
                                              HttpServletResponse res) throws AuthenticationException {
    // Source code made by ThienNLNT
    try {
      // Source code made by ThienNLNT
      User credentials = new ObjectMapper()
              // Source code made by ThienNLNT
              .readValue(req.getInputStream(), User.class);
      // Source code made by ThienNLNT

      // Source code made by ThienNLNT
      return authenticationManager.authenticate(
              // Source code made by ThienNLNT
              new UsernamePasswordAuthenticationToken(
                      // Source code made by ThienNLNT
                      credentials.getUsername(),
                      // Source code made by ThienNLNT
                      credentials.getPassword(),
                      // Source code made by ThienNLNT
                      new ArrayList<>()));
      // Source code made by ThienNLNT
    } catch (IOException e) {
      // Source code made by ThienNLNT
      throw new RuntimeException(e);
      // Source code made by ThienNLNT
    }
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Override
  // Source code made by ThienNLNT
  protected void successfulAuthentication(HttpServletRequest req,
                                          // Source code made by ThienNLNT
                                          HttpServletResponse res,
                                          // Source code made by ThienNLNT
                                          FilterChain chain,
                                          // Source code made by ThienNLNT
                                          Authentication auth) throws IOException, ServletException {
    // Source code made by ThienNLNT

    // Source code made by ThienNLNT
    String token = JWT.create()
            // Source code made by ThienNLNT
            .withSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
            // Source code made by ThienNLNT
            .withExpiresAt(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
            // Source code made by ThienNLNT
            .sign(HMAC512(SecurityConstants.SECRET.getBytes()));
    // Source code made by ThienNLNT
    res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
    // Source code made by ThienNLNT
  }
}
