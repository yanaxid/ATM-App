package data.constant;

import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Data

public class BankCompany {

	public static BankCompany BRI = new BankCompany("BRI");
	public static BankCompany BNI = new BankCompany("BNI");
	public static BankCompany MANDIRI = new BankCompany("Mandiri");
	public static BankCompany BCA = new BankCompany("BCA");

	private String name;

	public static BankCompany getByOrder(int order) {

		switch (order) {
			case 0:
				return BRI;
			case 1:
				return BNI;
			case 2:
				return MANDIRI;
			case 3:
				return BCA;
			default:
				throw new IllegalArgumentException("Cannot find BankCompany of order " + order);
		}

	}

	public static List<BankCompany> values() {
		List<BankCompany> banks = Arrays.asList(BRI, BNI, MANDIRI, BCA);
		return banks;
	}
}
