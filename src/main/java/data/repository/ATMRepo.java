package data.repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import data.constant.BankCompany;
import data.model.ATM;
import data.model.Bank;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.NONE)
public class ATMRepo {

	private static final long DEFAULT_ATM_BALANCE = 25_000_000;
	private static final Set<ATM> store = new HashSet<>();

	static {
		store.add(
				ATM.builder()
				.bank(BankRepo.findBankByName(BankCompany.BRI.getName()).get())
				.balance(DEFAULT_ATM_BALANCE)
				.build());

		store.add(ATM.builder().bank(BankRepo.findBankByName(BankCompany.BNI.getName()).get())
				.balance(DEFAULT_ATM_BALANCE)
				.build());

		store.add(ATM.builder().bank(BankRepo.findBankByName(BankCompany.MANDIRI.getName()).get())
				.balance(DEFAULT_ATM_BALANCE)
				.build());

		store.add(ATM.builder().bank(BankRepo.findBankByName(BankCompany.BCA.getName()).get())
				.balance(DEFAULT_ATM_BALANCE)
				.build());
	}

	public static Optional<ATM> findATMByBank(Bank bank) {
		return store.stream().filter(item -> bank.equals(item.getBank())).findAny();
	}

}
