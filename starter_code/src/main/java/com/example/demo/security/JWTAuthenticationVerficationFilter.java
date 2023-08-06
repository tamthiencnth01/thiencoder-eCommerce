package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.example.demo.controllers.UserController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@Component
public class JWTAuthenticationVerficationFilter extends BasicAuthenticationFilter {

  private static final Logger log = LogManager.getLogger(JWTAuthenticationVerficationFilter.class);

  public JWTAuthenticationVerficationFilter(AuthenticationManager authManager) {
    super(authManager);
  }

  // Source code made by ThienNLNT
  @Override
  // Source code made by ThienNLNT
  protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
  // Source code made by ThienNLNT
          throws IOException, ServletException {
    // Source code made by ThienNLNT
    String header = req.getHeader(SecurityConstants.HEADER_STRING);
    // Source code made by ThienNLNT

    // Source code made by ThienNLNT
    if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
      // Source code made by ThienNLNT
      if (!"http://localhost:8888/api/user/create".equals(req.getRequestURL().toString())) {
        // Source code made by ThienNLNT
        log.error("Access Denied. {}", 403);
        // Source code made by ThienNLNT
      }
      // Source code made by ThienNLNT
      chain.doFilter(req, res);
      // Source code made by ThienNLNT
      return;
      // Source code made by ThienNLNT
    }
    // Source code made by ThienNLNT

    // Source code made by ThienNLNT
    UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
    // Source code made by ThienNLNT

    // Source code made by ThienNLNT
    SecurityContextHolder.getContext().setAuthentication(authentication);
    // Source code made by ThienNLNT
    chain.doFilter(req, res);
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
    // Source code made by ThienNLNT
    String token = req.getHeader(SecurityConstants.HEADER_STRING);
    // Source code made by ThienNLNT
    if (token != null) {
      // Source code made by ThienNLNT
      String user = JWT.require(HMAC512(SecurityConstants.SECRET.getBytes())).build()
              // Source code made by ThienNLNT
              .verify(token.replace(SecurityConstants.TOKEN_PREFIX, ""))
              // Source code made by ThienNLNT
              .getSubject();
      // Source code made by ThienNLNT
      if (user != null) {
        // Source code made by ThienNLNT
        return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
        // Source code made by ThienNLNT
      }
      // Source code made by ThienNLNT
      return null;
      // Source code made by ThienNLNT
    }
    // Source code made by ThienNLNT
    return null;
    // Source code made by ThienNLNT
  }

}
