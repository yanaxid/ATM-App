package data.model;

import data.constant.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter

public class Transaction {
	
  private String id;
  private String timestamp;
  private Customer customer;
  private TransactionType type;
  private double expense;
  
 
}
