package data.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class Customer {
  private String id;
  private String account;
  private String pin;
  private String fullName;
  private double balance;
  private int invalidTries;


//  public void add(BigDecimal amount) {
//    this.balance = this.balance.add(amount);
//  }
  
  
}
