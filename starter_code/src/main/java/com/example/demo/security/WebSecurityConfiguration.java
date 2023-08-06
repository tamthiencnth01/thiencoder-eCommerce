package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  private final UserDetailsServiceImpl userDetailsService;
  private final BCryptPasswordEncoder bCryptPasswordEncoder;

  public WebSecurityConfiguration(UserDetailsServiceImpl userDetailsService,
                                  BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.userDetailsService = userDetailsService;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  // Source code made by ThienNLNT
  @Override
  // Source code made by ThienNLNT
  protected void configure(HttpSecurity http) throws Exception {
    // Source code made by ThienNLNT
    http.cors().and().csrf().disable().authorizeRequests()
            // Source code made by ThienNLNT
            .antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL).permitAll()
            // Source code made by ThienNLNT
            .anyRequest().authenticated()
            // Source code made by ThienNLNT
            .and()
            // Source code made by ThienNLNT
            .addFilter(new JWTAuthenticationFilter(authenticationManager()))
            // Source code made by ThienNLNT
            .addFilter(new JWTAuthenticationVerficationFilter(authenticationManager()))
            // Source code made by ThienNLNT
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));;
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Override
  // Source code made by ThienNLNT
  @Bean
  // Source code made by ThienNLNT
  public AuthenticationManager authenticationManagerBean() throws Exception {
    // Source code made by ThienNLNT
    return super.authenticationManagerBean();
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Override
  // Source code made by ThienNLNT
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    // Source code made by ThienNLNT
    auth.parentAuthenticationManager(authenticationManagerBean())
            // Source code made by ThienNLNT
            .userDetailsService(userDetailsService)
            // Source code made by ThienNLNT
            .passwordEncoder(bCryptPasswordEncoder);
    // Source code made by ThienNLNT
  }
}
