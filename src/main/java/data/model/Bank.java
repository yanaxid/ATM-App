package data.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter(AccessLevel.NONE)

public class Bank {
	
  private String id;
  private String name;
  private Boolean depositFeature;
  private double maxExpensePerWithdrawal;
  private double maxExpensePerUserDaily;
  @Builder.Default
  private Set<Customer> customers = new HashSet<>();
  @Builder.Default
  private Set<Transaction> transactions = new HashSet<>();
  
  
  public boolean hasDepositFeature() {
    return this.depositFeature.booleanValue();
  }

  public Optional<Customer> findCustomerByAccount(String account) {
    return customers.stream().filter(item -> account.equals(item.getAccount())).findAny();
  }

  public Set<Transaction> findAllTransactionsByAccount(String account) {
    return transactions.stream().filter(item -> account.equals(item.getCustomer().getAccount()))
        .collect(Collectors.toSet());
  }
}
