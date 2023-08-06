package com.example.demo.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserRequest {


  @JsonProperty
  private String password;

  @JsonProperty
  private String confirmPassword;

  @JsonProperty
  private String username;
}
