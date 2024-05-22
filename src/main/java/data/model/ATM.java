package data.model;


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

public class ATM {
	private String id;
	private double balance;
	private Bank bank;

//	public void subtract(BigDecimal amount) {
//		this.balance = this.balance.subtract(amount);
//	}
}
