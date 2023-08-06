package com.example.demo.model.persistence;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cart")
@Getter
@Setter
public class Cart {



  @ManyToMany
  @JsonProperty
  @Column
  private List<Item> items;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonProperty
  @Column
  private Long id;

  @OneToOne(mappedBy = "cart")
  @JsonProperty
  private User user;

  @Column
  @JsonProperty
  private BigDecimal total;
  public void addItem(Item item) {
    // Source code made by ThienNLNT
    if (items == null) {
      // Source code made by ThienNLNT
      items = new ArrayList<>();
      // Source code made by ThienNLNT
    }
    // Source code made by ThienNLNT
    items.add(item);
    // Source code made by ThienNLNT
    if (total == null) {
      // Source code made by ThienNLNT
      total = new BigDecimal(0);
      // Source code made by ThienNLNT
    }
    // Source code made by ThienNLNT
    total = total.add(item.getPrice());
    // Source code made by ThienNLNT
  }
  // Source code made by ThienNLNT

  // Source code made by ThienNLNT
  public void removeItem(Item item) {
    // Source code made by ThienNLNT
    if (items == null) {
      // Source code made by ThienNLNT
      items = new ArrayList<>();
      // Source code made by ThienNLNT
    }
    // Source code made by ThienNLNT
    items.remove(item);
    // Source code made by ThienNLNT
    if (total == null) {
      // Source code made by ThienNLNT
      total = new BigDecimal(0);
      // Source code made by ThienNLNT
    }
    // Source code made by ThienNLNT
    total = total.subtract(item.getPrice());
    // Source code made by ThienNLNT
  }
}
