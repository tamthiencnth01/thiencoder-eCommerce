package com.example.demo.model.persistence;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
@Getter
@Setter
public class Item {


  @Column(nullable = false)
  @JsonProperty
  private String name;

  @Column(nullable = false)
  @JsonProperty
  private BigDecimal price;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty
  private Long id;

  @Column(nullable = false)
  @JsonProperty
  private String description;

  @Override
  public int hashCode() {
    // Source code made by ThienNLNT
    final int prime = 31;
    // Source code made by ThienNLNT
    int result = 1;
    // Source code made by ThienNLNT
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    // Source code made by ThienNLNT
    return result;
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  @Override
  // Source code made by ThienNLNT
  public boolean equals(Object obj) {
    // Source code made by ThienNLNT
    if (this == obj)
      // Source code made by ThienNLNT
      return true;
    // Source code made by ThienNLNT
    if (obj == null)
      // Source code made by ThienNLNT
      return false;
    // Source code made by ThienNLNT
    if (getClass() != obj.getClass())
      // Source code made by ThienNLNT
      return false;
    // Source code made by ThienNLNT
    Item other = (Item) obj;
    // Source code made by ThienNLNT
    if (id == null) {
      // Source code made by ThienNLNT
      if (other.id != null)
        // Source code made by ThienNLNT
        return false;
      // Source code made by ThienNLNT
    } else if (!id.equals(other.id))
      // Source code made by ThienNLNT
      return false;
    // Source code made by ThienNLNT
    return true;
    // Source code made by ThienNLNT
  }
}
