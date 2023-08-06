package com.example.demo.model.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModifyCartRequest {


  @JsonProperty
  private long itemId;

  @JsonProperty
  private String username;
  @JsonProperty
  private int quantity;
}
